package org.example.multiplex.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import static org.example.utils.PrintUtils.printMsg;

public class Client2 {
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8090))) {
            ByteBuffer buffer = ByteBuffer.allocate(128);

            socketChannel.write(ByteBuffer.wrap("hello server,I am Client2".getBytes()));

            int read = socketChannel.read(buffer);
            printMsg("read msg from server: " + new String(buffer.array(), 0, read));

        }
    }
}
