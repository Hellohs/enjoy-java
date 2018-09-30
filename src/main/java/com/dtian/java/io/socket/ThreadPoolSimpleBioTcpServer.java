package com.dtian.java.io.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池模式的BIO TCP服务端
 * 因为，普通的BIO模式，一个客户端，对应一个线程，
 * 当客户端连接不断增多，线程数量增多，线程开销变大
 * 服务端瓶颈凸显，所以，对于这种类似的场景。
 * 大神提出，将每次创建的线程，替换成线程池，这样，线程由线程池管理，
 * 就不会因为 需要不断创建线程导致线程增多而崩溃了。
 * 但是同样会导致，另外一个问题，
 * 因为，线程池逻辑是 当核心线程被占满，之后的任务都会提交队列，
 * 所以 还是会因为客户端的增多 而 导致 消息处理的延时
 *
 * 所以，我们才那么迫切的需要NIO
 * @author Dingxc
 */
public class ThreadPoolSimpleBioTcpServer implements Runnable{
    private int port;
    final ExecutorService threadPool;

    public ThreadPoolSimpleBioTcpServer(int port) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(2);
    }

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            /**监听端口*/
            server = new ServerSocket(port);
            while (!Thread.interrupted()){
                /** 放到线程池 处理，避免了，一个连接一个线程的模式
                 * 但是同样存在瓶颈 */
                threadPool.submit(new SimpleBioTcpServerHandler(server.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadPoolSimpleBioTcpServer server = new ThreadPoolSimpleBioTcpServer(1234);
        new Thread(server).start();
    }
}
