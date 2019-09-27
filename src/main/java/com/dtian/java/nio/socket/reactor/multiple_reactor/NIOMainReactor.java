package com.dtian.java.nio.socket.reactor.multiple_reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 主从 reactor 多线程模型
 * MainReactor用来处理 accept事件，
 * SubReactor用来处理 read write 事件
 * Handler用来处理 解码->计算->编码 过程
 *
 * @author Dingxc
 */
    public class NIOMainReactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;
    final NIOSubReactorPool subReactorPool;

    public NIOMainReactor(int port) throws IOException {

        this.subReactorPool = new NIOSubReactorPool("subreactor-pool", 4);
        /**创建（打开）一个多路复用器*/
        this.selector = Selector.open();
        /** 打开通道 */
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", port));
        /** 配置通道非阻塞 */
        this.serverSocketChannel.configureBlocking(false);
        /** 注册ACCEPT事件 */
        SelectionKey key = this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new NIOAcceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                selector.select(1);//轮询选择
                Set<SelectionKey> selected;
                synchronized (this){
                    selected = selector.selectedKeys();
                }
                Iterator<SelectionKey> ite = selected.iterator();
                while (ite.hasNext()){
                    SelectionKey key = ite.next();
                    /**只处理Acceptable的key*/
                    if(key.isValid() && key.isAcceptable()){//被选中
                        NIOAcceptor acceptor = (NIOAcceptor) key.attachment();
                        if(acceptor != null)
                            acceptor.run();// NIOAcceptor run
                    }
                    ite.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把socket 注册给subReactor
     */
    class NIOAcceptor implements Runnable {

        @Override
        public void run() {
            SocketChannel socketChannel;
            try {
                socketChannel = serverSocketChannel.accept();
                if(socketChannel != null){
                    NIOSubReactor subReactor = subReactorPool.getNextSubReactor();
                    subReactor.register(socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
