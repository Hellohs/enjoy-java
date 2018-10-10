package com.dtian.java.nio.socket.reactor.multiple_reactor;

import java.io.IOException;

public class NIOMultipleReactorServer {
    public static void main(String[] args) {
        try {
            NIOMainReactor mainReactor = new NIOMainReactor(1237);
            new Thread(mainReactor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
