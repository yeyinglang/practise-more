package org.example.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(3);
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf)msg;
                                System.out.println("receive msg from server :" + byteBuf.toString(StandardCharsets.UTF_8));
//                                ctx.writeAndFlush("hello I am client");
//                                super.channelRead(ctx, msg);
                            }

                        });
                    }
                });
        ChannelFuture connect = bootstrap.connect("localhost",8080);
        connect.channel().writeAndFlush(Unpooled.wrappedBuffer("hello i am client".getBytes(StandardCharsets.UTF_8)));
//        connect.channel().writeAndFlush("??? helo i am client");
    }
}
