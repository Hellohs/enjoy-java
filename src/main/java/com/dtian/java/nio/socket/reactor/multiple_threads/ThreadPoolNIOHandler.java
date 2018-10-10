package com.dtian.java.nio.socket.reactor.multiple_threads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolNIOHandler implements Runnable {

    /** 2个线程的线程池 */
    final static ExecutorService WORKERS = Executors.newFixedThreadPool(2);

    private SocketChannel socketChannel;
    //private Selector selector;
    private SelectionKey key;

    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1, PROCESSING = 2;
    int status = READING;
    byte[] message = null;

    public ThreadPoolNIOHandler(Selector selector, SocketChannel socketChannel) throws IOException {

        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        // Optionally try first read now
        //this.sk = socket.register(selector, SelectionKey.OP_ACCEPT);
        //在每个handler 中，每次先register 0,覆盖之前选择的值，
        //什么都不选，也有取消之前监听的意思
        this.key = socketChannel.register(selector, 0);
        //把自己作为attachment 添加到 SelectionKey中，
        //这样 ThreadPoolNIOReactor run方法能调用到Handler中的run
        key.attach(this);
        key.interestOps(SelectionKey.OP_READ);
        /**唤醒多路复用器*/
        selector.wakeup();
    }

    @Override
    public void run() {
        if (status == READING && key.isValid() && key.isReadable()) {
            try {
                message = read();
                //将 解码->计算->编码的过程，放到线程池中处理
                status = PROCESSING;
                Future<byte[]> future = WORKERS.submit(new NIOProcessor(message));
                //如果此处，使用future.get()，
                // 因为服务端只有一个线程，当线程运行到此处，服务端线程还是会阻塞，
                //后面的客户端还是会等待
                //message = future.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (status == SENDING && key.isValid() && key.isReadable()) {
            //输出
            try {
                if(message != null){
                    write(message);
                    System.out.println("Sever send message successful.");
                    status = READING;
                    key.interestOps(SelectionKey.OP_READ);
                    key.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] read() throws IOException {
        int num = socketChannel.read(input);
        byte[] t_msg = null;
        if(num > 0){
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

    private void write(byte[] msg) throws IOException {
        output.clear();
        output.put(message);
        output.flip();
        socketChannel.write(output);
    }

    /**
     * 处理程序
     */
    class NIOProcessor implements Callable<byte[]> {
        private byte[] input;

        public NIOProcessor(byte[] input) {
            this.input = input;
        }

        @Override
        public byte[] call() throws Exception {
            String content = null;
            if (input != null) {
                content = new String(input, "UTF-8");
                if ("3".equals(content)) {
                    try {
                        //模拟 处理 时间很长的任务
                        Thread.sleep(10 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                content = "Add server tag-->" + content;
                message = content.getBytes();
            }
            //计算完成
            status = SENDING;
            key.interestOps(SelectionKey.OP_WRITE);
            key.selector().wakeup();
            //write(message);
            return message != null ? message : null;
        }
    }
}
