package org.example.multiplex.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

import static org.example.utils.PrintUtils.printMsg;

public class Client {
    public static void main(String[] args) {
        try(SocketChannel socketChannel = SocketChannel.open()){
            printMsg("begin connect");
            socketChannel.connect(new InetSocketAddress(8090));
            printMsg("send msg to server");
            socketChannel.write(ByteBuffer.wrap("hello server, I am client".getBytes()));
            printMsg("receive msg from server");
            ByteBuffer buffer = ByteBuffer.allocate(128);
            int read = socketChannel.read(buffer);
            printMsg("msg from server: " + new String(buffer.array(),0, read));
            TimeUnit.SECONDS.sleep(30);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
