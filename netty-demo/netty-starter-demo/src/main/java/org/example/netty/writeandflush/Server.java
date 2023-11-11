package org.example.netty.writeandflush;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * 测试writeAndFlush的流程；
 * 设置两个handler，
 * 1. ResponseSampleEncoder： 实现了encode方法； 用于加密；
 * 2. RequestSampleHandler：实现channelRead方法，用于读取来自客户端的请求内容，并封装成一个ResponseSample返回给客户端；
 * <p>
 * HeadContext -> decoder -->MsgHandelr --> TailContext 读取过程完毕；
 * TailContext-->MsgHandler--->encoder -->HeadContext --writeAndFlush 出栈过程完毕；
 *
 * 对于本例子来说：
 * header->FixedLengthFrameDecoder -->ResponseSampleEncoder(do nothing)--> RequestSampleHandler --> TailContext
 * TailContext --> RequestSampleHandler --> ResponseSampleEncoder(encode) -->header;
 *
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .group(bossGroup, workerGroup)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FixedLengthFrameDecoder(10))
                                .addLast(new ResponseSampleEncoder())
                                .addLast(new RequestSampleHandler());
                    }
                });
        ChannelFuture bind = serverBootstrap.bind(8080).sync();
        bind.channel().closeFuture().sync();
        System.out.println("shutdown");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();


    }
}
