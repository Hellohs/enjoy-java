package com.dtian.java.io.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于BIO 的TCP服务端
 * 同步阻塞，需要while循环不断 轮训 是否有accept。
 * 1.如果服务端是单线程模式，那么一个缓慢消息的处理会影响到其他客户端的相应，怀疑是不是坏了。
 * 2.如果是一个客户端对应一个服务端线程的模式，那么服务端性能会随着客户端的增加而降低
 * 因为服务器资源有限，并且线程之间切换需要时间和资源
 * 3.如果是线程池模式的服务端，因为线程池的线程资源也会在客户端增加到一定数量后而达到瓶颈。
 * 而且，线程池中的消息，在核心线程被暂满后，会存队列，会影响到消息的处理（感觉有延时）
 * <p>
 * 从本质上来说，此类BIO服务端，不适合 高并发高访问量 的场景
 * 适合连接数不多的，消息个体比较大的场景，比如：下载一个大文件的场景。
 * <p>
 * 优点是：编码简单，代码通俗易懂
 * <p>
 * 同时，此类TCP消息的处理都需要考虑TCP拆包，半包，粘包
 * <p>
 * 此处，我们先来看一个单线程的BIO Server
 *
 * @author Dingxc
 */
public class SimpleBioTcpServer implements Runnable {
    private int port = 1234;
    private static int sequence = 0;

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            /**打开一个端口*/
            server = new ServerSocket(port);
            System.out.println("server start ok.");
            while (!Thread.interrupted()) {
                /**不断监听是否有accept事件的发送，如果有，处理事件*/
                new Thread(new SimpleBioTcpServerHandler(server.accept())).start();
            }
        } catch (IOException e) {
            /**需要处理异常*/
            e.printStackTrace();
            try {
                server.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**单线程*/
        new Thread(new SimpleBioTcpServer()).start();
    }
}
