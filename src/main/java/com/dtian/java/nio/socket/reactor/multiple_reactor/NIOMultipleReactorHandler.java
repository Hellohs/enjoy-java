package com.dtian.java.nio.socket.reactor.multiple_reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 真正处理的Handler
 */
public class NIOMultipleReactorHandler implements Runnable {
    /**2个线程的线程池*/
    final static ExecutorService WORKERS = Executors.newFixedThreadPool(2);
    final SocketChannel channel;
    final Selector selector;

    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1, PROCESSING = 2;
    int status = READING;
    byte[] message = null;

    private SelectionKey sk;

    public NIOMultipleReactorHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);

        this.selector = selector;

        sk = channel.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        // 使选择器上的第一个还没有返回的选择操作立即返回
        selector.wakeup();//注册OP_READ兴趣之后，让select()方法返回，接受要读取的数据
    }

    @Override
    public void run() {
        //从通道中读
        if (status == READING && sk.isValid() && sk.isReadable()) {
            //从通道中读数据
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
            } /*catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
        }
        //往通道中写
        if (status == SENDING && sk.isValid() && sk.isWritable()) {
            //输出
            try {
                if(message != null){
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

    private byte[] read() throws IOException {
        int num = channel.read(input);
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
        channel.write(output);
    }

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
            sk.interestOps(SelectionKey.OP_WRITE);
            sk.selector().wakeup();
            //write(message);
            return message != null ? message : null;
        }
    }
}
