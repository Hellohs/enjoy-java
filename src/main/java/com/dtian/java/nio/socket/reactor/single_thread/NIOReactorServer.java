package com.dtian.java.nio.socket.reactor.single_thread;

import java.io.IOException;

public class NIOReactorServer {
    public static void main(String[] args) {
        NIOReactor reactor = null;
        try {
            reactor = new NIOReactor(1237);
            //启动一个线程
            new Thread(reactor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
