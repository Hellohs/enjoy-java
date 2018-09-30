package com.dtian.java.nio.socket;

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

/**
 * NIO TCP客户端
 *
 * @author Dingxc
 */
public class SimpleNioTcpClient implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    static final int CAPACITY = 128;

    public SimpleNioTcpClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            //创建SocketChannel
            this.socketChannel = SocketChannel.open();
            //建立连接
            //socketChannel.connect(new InetSocketAddress(host, port));
            this.socketChannel.configureBlocking(false);
            //创建多路复用器
            this.selector = Selector.open();
        } catch (IOException e) {
            //需要处理异常
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        /**尝试建立连接*/
        try {
            doConnect();
            //建立连接成功
            new Thread(new MessageThread()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SelectionKey key = null;
        while (!Thread.interrupted()) {
            try {
                selector.select(50);
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> ite = selected.iterator();
                while (ite.hasNext()) {
                    key = ite.next();
                    ite.remove();
                    handle(key);
                }
            } catch (IOException e) {
                if (key != null) {
                    key.cancel();
                }
                if (key.channel() != null) {
                    try {
                        key.channel().close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
    }

    private void doConnect() throws IOException {
        if (this.socketChannel.connect(new InetSocketAddress(host, port))) {
            //如果已经连接上，注册read事件
            this.socketChannel.register(this.selector, SelectionKey.OP_READ, ByteBuffer.allocate(CAPACITY));
        } else {
            //反之还没连接上，注册connect事件
            this.socketChannel.register(this.selector, SelectionKey.OP_CONNECT, ByteBuffer.allocate(CAPACITY));
            System.out.println("Client Connect Success.");
        }
    }

    private void handle(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (key.isValid() && key.isConnectable()) {
            if (channel.finishConnect()) {
                channel.register(selector, SelectionKey.OP_READ);
            }
        }
        if (key.isValid() && key.isReadable()) {
            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
            int num = channel.read(buffer);
            if (num > 0) {
                buffer.flip();
                byte[] messageBytes = new byte[buffer.remaining()];
                buffer.get(messageBytes);
                String message = new String(messageBytes, "UTF-8");
                System.out.println("Client Get Msg:" + message);
            } else if (num < 0) {
                key.cancel();
            }
        }
    }

    private void doWrite(SocketChannel channel, String message) throws IOException {
        byte[] messageBytes = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(messageBytes.length);
        buffer.put(messageBytes);
        buffer.flip();
        channel.write(buffer);
        System.out.println("client send msg: " + new String(messageBytes));
    }

    class MessageThread implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String inputMessage = null;
                while ((inputMessage = input.readLine()) != null) {
                    doWrite(socketChannel, inputMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new SimpleNioTcpClient("127.0.0.1", 5678)).start();
    }
}
