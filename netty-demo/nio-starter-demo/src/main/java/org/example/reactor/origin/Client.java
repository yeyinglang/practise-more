package org.example.reactor.origin;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.example.utils.PrintUtils.printMsg;

public class Client {
    public static void main(String[] args) {
        //创建一个新的SocketChannel，一会通过通道进行通信
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8090));
             Scanner scanner = new Scanner(System.in)){
            System.out.println("已连接到服务端！");
            while (true) {   //咱给它套个无限循环，这样就能一直发消息了
                System.out.println("请输入要发送给服务端的内容：");
                String text = scanner.nextLine();
                //直接向通道中写入数据，真舒服
                channel.write(ByteBuffer.wrap(text.getBytes()));
                System.out.println("已发送！");
                ByteBuffer buffer = ByteBuffer.allocate(128);
                channel.read(buffer);   //直接从通道中读取数据
                buffer.flip();
                System.out.println("收到服务器返回："+new String(buffer.array(), 0, buffer.remaining()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public static void main(String[] args) {
//        try(Socket socket = new Socket()) {
//            socket.connect(new InetSocketAddress(8090));
//
//            socket.getOutputStream().write("hello server, i am Client1".getBytes(StandardCharsets.UTF_8));
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes = new byte[1280];
//            int k = inputStream.read(bytes);
//            printMsg("receive msg from server, ", new String(bytes,0, k));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        try(SocketChannel socketChannel = SocketChannel.open(); Selector selector = Selector.open()){
////
////            socketChannel.register(selector, SelectionKey.OP_CONNECT);
////            socketChannel.configureBlocking(false);
////            socketChannel.connect(new InetSocketAddress(8899));
////
////
////
////        }catch (Exception e) {
////            e.printStackTrace();
////        }
//
//    }
}
