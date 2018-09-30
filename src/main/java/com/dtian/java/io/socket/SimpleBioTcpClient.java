package com.dtian.java.io.socket;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * 基于BIO的 tcp 客户端
 * @author Dingxc
 */
public class SimpleBioTcpClient {
    private String host;
    private int port;
    private Socket socket;

    public SimpleBioTcpClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            /**初始化 客户端socket*/
            this.socket = initClient(host, port);
        } catch (IOException e) {
            /**需要处理异常*/
            e.printStackTrace();
        }
    }

    private Socket initClient(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public void sendMsg(String msg) {
        if (StringUtils.isEmpty(msg))
            return;
        /**获取输出流*/
        OutputStream output = null;
        try {
            output = socket.getOutputStream();
            /**输入流转换成包装流*/
            PrintWriter pw = new PrintWriter(output, true);
            pw.println(msg);
            System.out.println("client send msg: " + msg);

            /**获取输入流，准备接受服务端返回的消息*/
            InputStream input = socket.getInputStream();
            /**转换成包装流*/
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String response = null;
            if (StringUtils.isNotEmpty(response = br.readLine())) {
                System.out.println("client got response: " + response);
            }
            /**如果需要，在发送完消息后 关闭各种流 和 通道*/
//            pw.close();
//            output.close();
//            br.close();
//            input.close();
//            socket.close();
        } catch (IOException e) {
            /**需要处理异常*/
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleBioTcpClient client = new SimpleBioTcpClient("127.0.0.1", 1234);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputMessage = null;
        while((inputMessage = input.readLine()) != null) {
            client.sendMsg(inputMessage);
        }
    }
}
