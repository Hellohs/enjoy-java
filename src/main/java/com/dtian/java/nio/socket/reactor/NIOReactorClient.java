package com.dtian.java.nio.socket.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOReactorClient {
    public static int PORT = 1237;

    public static void main(String[] args) {
        new Thread(new NioEchoClient("127.0.0.1", PORT)).start();
    }

    static class NioEchoClient implements Runnable {
        public static int CAPACITY = 1024;//buffer容量
        final String host;//ip地址
        final int port;//端口

        private Selector selector;
        private SocketChannel socketChannel;

        private volatile boolean stop = false;

        public NioEchoClient(String host, int port) {
            this.host = host;
            this.port = port;
            try {
                //创建一个多路复用器
                this.selector = Selector.open();
                //创建通道
                this.socketChannel = SocketChannel.open();
                //设置通道非阻塞
                this.socketChannel.configureBlocking(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            new Thread(new MessageThread()).start();
            try {
                /**建立连接*/
                doConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SelectionKey sk = null;
            while (!stop) {
                try {
                    selector.select(1L);
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> ite = selected.iterator();
                    while (ite.hasNext()) {
                        sk = ite.next();
                        ite.remove();
                        handle(sk);
                    }
                } catch (IOException e) {
                    if (sk != null) {
                        sk.cancel();
                    }
                    if (sk.channel() != null) {
                        try {
                            sk.channel().close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    //e.printStackTrace();
                }
            }
        }

        private void doConnect() throws IOException {
            if (this.socketChannel.connect(new InetSocketAddress(host, port))) {
                //如果已经连接成功
                this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            } else {
                //如果还没有连接成功
                this.socketChannel.register(this.selector, SelectionKey.OP_CONNECT);
                System.out.println("Client Connect Success.");
            }
        }

        private void handle(SelectionKey sk) throws IOException {
            SocketChannel sc = (SocketChannel) sk.channel();
            if (sk.isValid() && sk.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                }
            }
            if (sk.isValid() && sk.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
                int num = sc.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] messageBytes = new byte[buffer.remaining()];
                    buffer.get(messageBytes);
                    String message = new String(messageBytes, "UTF-8");
                    System.out.println("Client Get Msg:" + message);
                } else if (num < 0) {
                    sk.cancel();
                }
            }
        }

        /** 客户端发送消息线程 */
        class MessageThread implements Runnable {

            @Override
            public void run() {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String inputMessage = null;
                    while((inputMessage = input.readLine()) != null) {
                        doWrite(socketChannel, inputMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void doWrite(SocketChannel channel, String message) throws IOException {
                byte[] messageBytes = message.getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(messageBytes.length);
                buffer.put(messageBytes);
                buffer.flip();
                channel.write(buffer);
                System.out.println("client send msg:" + new String(messageBytes));
            }
        }
    }
}
