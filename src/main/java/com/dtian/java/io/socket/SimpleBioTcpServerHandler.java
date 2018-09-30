package com.dtian.java.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleBioTcpServerHandler implements Runnable {
    private Socket socket;

    public SimpleBioTcpServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //InputStream input = null;
        //OutputStream output = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String msg = null;
            /**判断消息是否读取完成，如果是 -1 表示 客户端断开连接，此处来表示消息已经读取完成*/
            while ((msg = reader.readLine()) != null) {
                System.out.println("server receive msg:" + msg);
                /**处理消息*/
                String response = process(msg);
                /**处理完成消息后，往客户端回发*/
                writer.println(response);
                //writer.flush();
                /**到此，一个消息的处理就完成了，是不是很简单啊*/
            }
            //往客户端发送
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**关闭流*/
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**处理消息，需要包含，解码，计算，编码*/
    /**
     * 在此处就做简单的 字符串拼接
     *
     * @param msg
     * @return
     */
    private String process(String msg) {
        return "add server tag -> " + msg;
    }
}
