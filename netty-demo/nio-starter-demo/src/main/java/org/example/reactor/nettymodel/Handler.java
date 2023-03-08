package org.example.reactor.nettymodel;

import org.example.reactor.nettymodel.channel.NioSocketChannel;
import org.example.utils.PrintUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static org.example.utils.PrintUtils.printMsg;

public class Handler implements Runnable {
    private final NioSocketChannel nioSocketChannel;

    public Handler(NioSocketChannel nioSocketChannel) {
        this.nioSocketChannel = nioSocketChannel;
    }

    public SocketChannel socketChannel(){
        return this.nioSocketChannel.getSocketChannel();
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        try {
            int readLength = socketChannel().read(buffer);
            if(readLength<=0) {
                socketChannel().close();
                PrintUtils.printMsg("socketChannel closed");
                return;
            }
            buffer.flip();
//             处理业务；
            String info = new String(buffer.array(), 0, buffer.remaining());
            nioSocketChannel.pipeline().fireChannelRead(info);
            printMsg("get msg from client: ", info);

            socketChannel().write(ByteBuffer.wrap("hello client,  I am sever".getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
