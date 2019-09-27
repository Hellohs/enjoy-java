package com.dtian.java.nio.socket.reactor.multiple_reactor;

import com.dtian.java.nio.socket.reactor.multiple_threads.ThreadPoolNIOReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOSubReactor implements Runnable {
    private String name;
    private Selector selector;

    public NIOSubReactor(String name) {
        this.name = name;
    }

    /**
     * 启动subReactor
     */
    public void startup() {
        new Thread(this).start();
    }

    public void register(SocketChannel socketChannel){
        try {
            /**设置通道非阻塞*/
            socketChannel.configureBlocking(false);
            synchronized (this){
                selector.wakeup();//
                /**注册OP_READ事件*/
                SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
                /** 添加 NIOMultipleReactorHandler 至 上面的key中*/
                key.attach(new NIOMultipleReactorHandler(this.selector, socketChannel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            selector = Selector.open();
            while (!Thread.interrupted()){
                selector.select(1);
                Set<SelectionKey> selected;
                synchronized (this){
                    selected = selector.selectedKeys();
                }
                Iterator<SelectionKey> ite = selected.iterator();
                while (ite.hasNext()){
                    SelectionKey key = ite.next();
                    /**获取NIOMultipleReactorHandler*/
                    dispatch((Runnable) key.attachment());//dispatch（运行）NIOMultipleReactorHandler的run
                    ite.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void dispatch(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }
}
