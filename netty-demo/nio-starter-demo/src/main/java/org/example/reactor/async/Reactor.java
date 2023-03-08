package org.example.reactor.async;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import static org.example.utils.PrintUtils.printMsg;

public class Reactor implements Runnable, Closeable {
    private int subReactorNum;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    Reactor(int subReactorNum) {
        try{
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            this.subReactorNum = subReactorNum;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        this.serverSocketChannel.close();
        this.selector.close();
    }


    @Override
    public void run() {
        try{
            this.serverSocketChannel.bind(new InetSocketAddress(8090));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT,new Acceptor(serverSocketChannel,subReactorNum));
            printMsg("register accept success in reactor");
            while(true){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    dispatch(next);
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void dispatch(SelectionKey next) {
        Runnable attachment = (Runnable) next.attachment();
        attachment.run();
    }
}
