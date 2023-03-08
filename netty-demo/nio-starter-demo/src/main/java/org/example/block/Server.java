package org.example.block;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.example.utils.PrintUtils.printMsg;

/**
 * 最普通的阻塞式IO、
 * channel是双向的，流是单向的；所以要获取双向的流，进行读写；
 * <p>
 * BIO的例子；
 * 1 ServerSocket
 * 2 accept
 * 3 get output/input info ;
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        printMsg("start to binding");
        serverSocket.bind(new InetSocketAddress(8090));
//        while(true) {
//
//        }

        while (true) {
            // 如果第一个没有执行结束，那么第二个线程阻塞在这里。
            Socket socket = serverSocket.accept();
            printMsg("accept connect success");
            byte[] bytes = new byte[1024];
//            output
            printMsg("begin to write");
            OutputStream os = socket.getOutputStream();
            os.write("I am Server ,hello client".getBytes());


//            input
            printMsg("begin to read");
            InputStream is = socket.getInputStream();
            int read = is.read(bytes);
            String msg = new String(bytes, 0, read);
            System.out.println("msg: " + msg);
        }

    }

}
