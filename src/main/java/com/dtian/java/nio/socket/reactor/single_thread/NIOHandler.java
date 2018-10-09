package com.dtian.java.nio.socket.reactor.single_thread;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 具体Handler处理业务，
 * 一般包括：read->decode->compute->encode->send
 * 在创建Handler的时候，会将this 作为attachment 存入SelectionKey中，
 * 在 {@link NIOReactor} 中dispatch方法会调用run方法
 */
public class NIOHandler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1;
    int status = READING;
    byte[] message = null;

    public NIOHandler(Selector selector, SocketChannel socket) throws IOException {
        this.socket = socket;
        this.socket.configureBlocking(false);
        //在每个handler 中，每次先register 0,覆盖之前选择的值，
        //什么都不选，也有取消之前监听的意思
        sk = socket.register(selector, 0);
        //把自己添加成为attachment，这样自身run方法就能被调用了
        sk.attach(this);
        //设置对OP_READ 感兴趣
        sk.interestOps(SelectionKey.OP_READ);
        //让阻塞的线程 立刻返回
        selector.wakeup();
    }

    @Override
    public void run() {
        //从通道中读
        if (status == READING && sk.isValid() && sk.isReadable()) {
            try {
                //从通道中读数据
                message = read();
                //处理收到的消息
                message = process(message);
                status = SENDING;
                sk.interestOps(SelectionKey.OP_WRITE);
                sk.selector().wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //往通道中写
        if (status == SENDING && sk.isValid() && sk.isWritable()) {
            //输出
            try {
                if (message != null) {
                    write(message);
                    System.out.println("Sever send message successful.");
                    status = READING;
                    sk.interestOps(SelectionKey.OP_READ);
                    sk.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从socket中读，获取内容
     *
     * @return
     * @throws IOException
     */
    private byte[] read() throws IOException {
        int num = socket.read(input);
        byte[] t_msg = null;
        if (num > 0) {
            //读数据
            input.flip();
            t_msg = new byte[input.remaining()];
            input.get(t_msg);
            String msg = new String(t_msg, "UTF-8");
            System.out.println("server got msg:" + msg);
            input.clear();
        }
        return t_msg;
    }

    /**
     * 服务端处理内容，也许是特殊的业务处理；也许是其他
     *
     * @param inputMsg
     * @return
     * @throws UnsupportedEncodingException
     */
    private byte[] process(byte[] inputMsg) throws UnsupportedEncodingException {
        if (inputMsg != null) {
            String content = new String(inputMsg, "UTF-8");
            if ("3".equals(content)) {
                try {
                    Thread.sleep(10 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            content = "Add server tag-->" + content;
            return content.getBytes();
        }
        return null;
    }

    /**
     * 往客户端写
     *
     * @param msg
     * @throws IOException
     */
    private void write(byte[] msg) throws IOException {
        output.clear();
        output.put(message);
        output.flip();
        socket.write(output);
    }
}
