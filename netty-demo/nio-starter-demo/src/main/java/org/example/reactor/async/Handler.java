package org.example.reactor.async;

import org.example.utils.PrintUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static org.example.utils.PrintUtils.printMsg;

public class Handler implements Runnable {
    private final SocketChannel socketChannel;

    public Handler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        try {
            int readLength = socketChannel.read(buffer);
            if(readLength<=0) {
                socketChannel.close();
                PrintUtils.printMsg("socketChannel closed");
                return;
            }

            buffer.flip();
//             处理业务；
            printMsg("get msg from client: ", new String(buffer.array(), 0, buffer.remaining()));

            socketChannel.write(ByteBuffer.wrap("hello client,  I am sever".getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
