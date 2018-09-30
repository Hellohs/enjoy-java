package com.dtian.java.nio.socket.single_thread;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO TCP服务端，
 * 因为 BIO Server {@link com.dtian.java.io.socket.SimpleBioTcpServer}
 * 是同步阻塞的通道，而且会因为连接数的不断增加，导致服务器压力不断增加，性能降低。
 * <p>
 * 所以，在jdk1.4 中，大神们提出了NIO，
 * 相比于BIO，NIO的不同有以下三个方面
 * 1：增加了多路复用器（Selector），事件通知的模式，一个线程就能管理多个通道
 * 2：增加了buffer；channel，数据的读取和写入不再面向流，而是面向缓存区，填满刷新
 * 3：非阻塞IO，在select 不到事件的时候，不再像BIO那样阻塞线程，线程可以去做其他事情
 * 下面我们来看一个简单的NIO TCP Server
 *
 * 优点：
 *     相比于BIO，性能有很大的提升，不在一个连接一个线程，并且是非阻塞的
 * 缺点：
 *     编码相对复杂，并且不好扩展，还是会存在多个客户端相互等待的问题
 *
 * @author Dingxc
 */
public class SimpleNioTcpServer {
    private int port;
    private Selector selector;
    //获取的数据
    private String message;

    /**
     * 初始化 server
     *
     * @param port
     */
    public SimpleNioTcpServer(int port) throws IOException {
        this.port = port;
        /** 打开一个通道 */
        ServerSocketChannel channel = ServerSocketChannel.open();
        /**设置通道非阻塞*/
        channel.configureBlocking(false);
        /**获取通道的socket，并且绑定指定的端口，有类似于打开端口的意思？*/
        channel.socket().bind(new InetSocketAddress("127.0.0.1", port));
        //创建一个多路复用器  A multiplexor of {@link SelectableChannel} objects.
        selector = Selector.open();
        //将多路复用器，注册到通道上，并且关注ACCEPT事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 监听服务端端口
     */
    public void listen() throws IOException {
        /**selector 轮训*/
        while (!Thread.interrupted()) {
            //当有注册的事件到达时，方法返回，否则阻塞
            //selector.select();//可以通过调用 wakeup 或者 线程 interrupted来快速返回
            //selector.select(500L);//超时打断
            if (selector.select(500) == 0) {
                //在等待信道准备的同时，也可以异步地执行其他任务，这里打印*
                //System.out.print("*");
                continue;
            }
            //获取所有的被选中的事件的键，Returns this selector's selected-key set.
            Set<SelectionKey> selected = selector.selectedKeys();
            Iterator<SelectionKey> ite = selected.iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                if (key.isValid() && key.isAcceptable())
                    doAccept(key);
                if (key.isValid() && key.isReadable())
                    doRead(key);
                if (key.isValid() && key.isWritable())
                    doWrite(key);
                //删除已选的key，防止重复注册
                ite.remove();
            }
        }
    }

    /**
     * 处理accept 事件
     * @param key
     */
    private void doAccept(SelectionKey key) throws IOException {
        //获取事件所在通道
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        //获取socket channel
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Socket Remote Address: " + socketChannel.socket().getRemoteSocketAddress());
        //设置socket非阻塞
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        //注册读事件，准备从通道中获取数据
        //并且把 申请的buffer，通过attachment(附件的形式)，附加到通道中
        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
    }

    private void doRead(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            //获取客户端传输数据可读取消息通道
            socketChannel = (SocketChannel) key.channel();
            //获取附件，刚才将buffer存入通道中，现在获取附件
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            //清空缓存区中数据，准备获取数据
            buffer.clear();
            //Buffer有三个属性：
            //  1:capacity 表示buffer的大小，不管在什么情况下，capacity是不变的
            //  2:position 表示buffer当前位置，position 初始值为0，当数据写入，position会往后移
            //  3:limit 表示buffer最多能使用的量，在读模式下，就是capacity的值，在写模式下，就是position的位置
            //具体可以参看 OneNote中的总结.
            int read = socketChannel.read(buffer);
            //如果read 返回-1，表示数据读取完成，通道关闭
            if(read == -1){
                key.cancel();
                socketChannel.close();
                return;
            }
            //开始处理数据
            //目前状态，channel中的数据，已经写到buffer中，位置为：position
            //所以申请一个 position 大小的byte数组，
            byte[] data = new byte[buffer.position()];
            //翻转数据，开始获取
            buffer.flip();//flip会将position 置为0
            buffer.get(data);
            System.out.println("server got msg: " + new String(data, "UTF-8"));
            //处理数据
            message = process(data);
            key.interestOps(SelectionKey.OP_WRITE);
            selector.wakeup();
        } catch (IOException e) {
            //需要处理异常情况
            e.printStackTrace();
        }
    }

    private void doWrite(SelectionKey key) {
        try {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            if(StringUtils.isNotEmpty(message)){
                buffer.put(message.getBytes());
            }else{
                buffer.put("server response".getBytes());
            }

            buffer.flip();
            SocketChannel channel = (SocketChannel) key.channel();
            //将数据写入到信道中
            channel.write(buffer);
            if (!buffer.hasRemaining()) {
                //如果缓冲区中的数据已经全部写入了信道，则将该信道感兴趣的操作设置为可读
                key.interestOps(SelectionKey.OP_READ);
            }
            //为读入更多的数据腾出空间
            buffer.compact();
        } catch (IOException e) {
            //需要处理异常
            e.printStackTrace();
        }
    }

    private String process(byte[] data) throws UnsupportedEncodingException {
        if(data != null){
            return "add server tag -> " + new String(data, "UTF-8");
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            new SimpleNioTcpServer(5678).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
