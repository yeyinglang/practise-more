package org.example.multiplex.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import static org.example.utils.PrintUtils.printMsg;

public class Server {
    public static void main(String[] args) throws IOException {
        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();Selector selector = Selector.open();) {
            serverSocketChannel.bind(new InetSocketAddress(8090));
            printMsg("configure blocking");

//         true ，报错；
            serverSocketChannel.configureBlocking(false);

            printMsg("register selector");
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
//             select 会阻塞；获取所以已经就绪的任务；
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        int read = channel.read(buffer);
                        printMsg("read ",read+"");
                        if(read<0) {
                            printMsg("close client, so server will close channel");
                            channel.close();
                            break;
                        }
                        printMsg("read msg from client: " + new String(buffer.array(), 0, read));

//                     write
                        channel.write(ByteBuffer.wrap("I am Server".getBytes()));
                    }
                }
            }

        }

    }
}
