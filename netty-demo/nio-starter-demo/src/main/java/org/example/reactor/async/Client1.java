package org.example.reactor.async;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.example.utils.PrintUtils.printMsg;

public class Client1 {
    public static void main(String[] args) {
        try(Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(8090));

            socket.getOutputStream().write("hello server, i am Client11".getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[128];
            int k = inputStream.read(bytes);
            printMsg("receive msg from server, ", new String(bytes,0, k));
        }catch (Exception e) {
            e.printStackTrace();
        }

//        try(SocketChannel socketChannel = SocketChannel.open(); Selector selector = Selector.open()){
//
//            socketChannel.register(selector, SelectionKey.OP_CONNECT);
//            socketChannel.configureBlocking(false);
//            socketChannel.connect(new InetSocketAddress(8899));
//
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
