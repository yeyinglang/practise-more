package org.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.example.utils.PrintUtils.printMsg;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                printMsg(Thread.currentThread().getName(), ",收到server msg : >>", byteBuf.toString(StandardCharsets.UTF_8));
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(8090));
        while (true) {
            channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("hello server,I am Client".getBytes(StandardCharsets.UTF_8)));
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
