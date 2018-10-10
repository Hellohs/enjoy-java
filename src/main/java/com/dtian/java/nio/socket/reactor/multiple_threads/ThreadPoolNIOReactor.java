package com.dtian.java.nio.socket.reactor.multiple_threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用线程池的 nio reactor
 *
 * 在单线程reactor模式中，
 *     所有业务的处理逻辑都在Handler中，如果其中一个业务处理非常缓慢，
 *     就会影响到其他业务，所以，大神们又提出，把这些处理的逻辑，放到线程池中
 *
 * 线程池是能够被管理的，资源可控，而且业务处理之间相互独立，一个缓慢业务的处理不会影响其他业务
 *
 * @author Dingxc
 */
public class ThreadPoolNIOReactor implements Runnable {
    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    public ThreadPoolNIOReactor(int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", port));
        this.serverSocketChannel.configureBlocking(false);
        /**注册通道*/
        SelectionKey key = this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new ThreadPoolNIOAcceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(1);
                Set<SelectionKey> selected;
                synchronized (this){
                    selected = selector.selectedKeys();
                }
                Iterator<SelectionKey> ite = selected.iterator();
                //分发连接请求
                while (ite.hasNext()){
                    SelectionKey key = ite.next();
                    dispatch(key);
                    ite.remove();
                }
                //selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**分发处理逻辑*/
    private void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if (r != null)
            r.run();
    }

    class ThreadPoolNIOAcceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(socketChannel != null)
                    new ThreadPoolNIOHandler(selector, socketChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
