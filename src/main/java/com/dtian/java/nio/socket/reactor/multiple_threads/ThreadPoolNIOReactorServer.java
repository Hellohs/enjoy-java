package com.dtian.java.nio.socket.reactor.multiple_threads;

import java.io.IOException;

public class ThreadPoolNIOReactorServer {
    public static void main(String[] args) {
        try {
            ThreadPoolNIOReactor reactor = new ThreadPoolNIOReactor(1237);
            new Thread(reactor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
