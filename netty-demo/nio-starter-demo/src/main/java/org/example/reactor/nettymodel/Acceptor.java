package org.example.reactor.nettymodel;

import org.example.reactor.nettymodel.channel.ChannelHandler;
import org.example.reactor.nettymodel.channel.ChannelInitializer;
import org.example.reactor.nettymodel.channel.DefaultChannelPipeline;
import org.example.reactor.nettymodel.channel.NioSocketChannel;
import org.example.utils.PrintUtils;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class Acceptor implements Runnable {
    private final ChannelHandler childHandler;
    private ServerSocketChannel serverSocketChannel;
    private MyEventLoopAsyncExecutor myEventLoopAsyncExecutor;

    public Acceptor(ServerSocketChannel socketChannel, int subReactorNum, ChannelHandler childHandler) {
        this.serverSocketChannel = socketChannel;
        this.myEventLoopAsyncExecutor = new MyEventLoopAsyncExecutor(subReactorNum);
        this.childHandler = childHandler;
    }


    @Override
    public void run() {
        try{
            PrintUtils.printMsg("begin acceptor");
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            NioSocketChannel nioSocketChannel = new NioSocketChannel(socketChannel, new DefaultChannelPipeline());
            ((ChannelInitializer)childHandler).initChannel(nioSocketChannel);
            Selector selector = myEventLoopAsyncExecutor.next();
            selector.wakeup();
            socketChannel.register(selector, SelectionKey.OP_READ, new Handler(nioSocketChannel));
            PrintUtils.printMsg("bind success >> client address=", socketChannel.getRemoteAddress());
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
