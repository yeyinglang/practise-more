package org.example.block;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.example.utils.PrintUtils.printMsg;

/**
 * 随即，客户端2就开始连接server，
 * 阻塞在read方法上了。
 */
public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        printMsg("begin connect");
        socket.connect(new InetSocketAddress(8090));
        byte[] bytes = new byte[1024];
//        while(true) {
//            output
            printMsg("begin to write");
            OutputStream os = socket.getOutputStream();
            os.write("I am Client2 ,hello server".getBytes());

//            input
            printMsg("begin to read");
            InputStream is = socket.getInputStream();
            int read = is.read(bytes);
            String msg = new String(bytes, 0, read);
            System.out.println("msg: " + msg);
//        }
    }
}
