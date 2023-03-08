package org.example.block;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.example.utils.PrintUtils.printMsg;

/**
 * 客户端一，连接后，睡眠10s，
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        printMsg("begin connect");
        socket.connect(new InetSocketAddress(8090));
        byte[] bytes = new byte[1024];
//        while(true) {
//            output
        printMsg("begin to write");
        TimeUnit.SECONDS.sleep(10);
        printMsg("sleep over");
        OutputStream os = socket.getOutputStream();
        os.write("I am Client ,hello server".getBytes());

//            input
        printMsg("begin to read");
        InputStream is = socket.getInputStream();
        int read = is.read(bytes);
        String msg = new String(bytes, 0, read);
        System.out.println("msg: " + msg);
    }
}
