package com.dtian.java.nio.socket.reactor.single_thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单线程 TCP socket 通信，Reactor模式
 *
 * 因为 之前 jdk1.4 自带的NIO socket通信，编程困难，并且不好扩展，而且还是存在性能瓶颈
 * 所以 大神们在NIO的基础上，又提出了 Reactor模式。
 *
 * Reactor是一种通过调度适当的处理程序来相应IO事件的设计模式。类似于AWT。
 * 里面包含：
 *     Reactor：IO事件的派发者，将IO事件分派给对应的Handler
 *     Acceptor：接收Client连接，将对应Client和Handler建立对应关系
 *     Handler：和一个client通讯的实体，执行非阻塞读写任务，还包括对业务的处理。
 *
 * 优点：
 *     各个角色各司其职，模型相对清晰，相比与NIO 服务端，代码易读易懂，易于扩展。
 * 缺点：
 *     读写，编码解码，处理逻辑都包含在Handler中，如果一个线程处理缓慢，会影响其他客户端
 *
 * @author Dingxc
 *
 */
public class NIOReactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    public NIOReactor(int port) throws IOException {
        //创建 多路复用器
        selector = Selector.open();
        //打开ServerSocketChannel
        serverSocket = ServerSocketChannel.open();
        //绑定端口
        serverSocket.socket().bind(new InetSocketAddress("127.0.0.1", port));
        //设置通道非阻塞
        serverSocket.configureBlocking(false);
        //注册多路复用器到通道
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new NIOAcceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(1);
                Set<SelectionKey> selected;
                synchronized (this) {
                    selected = selector.selectedKeys();
                }
                Iterator<SelectionKey> ite = selected.iterator();
                while (ite.hasNext()){
                    SelectionKey key = ite.next();
                    dispatch(key);
                    ite.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * dispatch处理器
     * @param sk
     */
    private void dispatch(SelectionKey sk) {
        Runnable r = (Runnable) sk.attachment();
        if (r != null)
            r.run();
    }

    /**
     * 接收 accept 事件，创建Handler对应关系
     */
    class NIOAcceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socket = serverSocket.accept();
                if (socket != null)
                    new NIOHandler(selector, socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
