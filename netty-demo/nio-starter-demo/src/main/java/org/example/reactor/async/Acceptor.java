package org.example.reactor.async;

import org.example.utils.PrintUtils;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class Acceptor implements Runnable {
    private ServerSocketChannel serverSocketChannel;

    private AtomicInteger idx = new AtomicInteger();
    private MyEventLoopAsyncExecutor myEventLoopAsyncExecutor;

    public Acceptor(ServerSocketChannel socketChannel, int subReactorNum) {
        this.serverSocketChannel = socketChannel;
        this.myEventLoopAsyncExecutor = new MyEventLoopAsyncExecutor(subReactorNum);
    }


    @Override
    public void run() {
        try{
            PrintUtils.printMsg("begin acceptor");
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            Selector selector = myEventLoopAsyncExecutor.next();
            selector.wakeup();
            socketChannel.register(selector, SelectionKey.OP_READ, new Handler(socketChannel));
            PrintUtils.printMsg("bind success >> client address=", socketChannel.getRemoteAddress());
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
