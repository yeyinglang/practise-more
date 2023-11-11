package org.example.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
//        搞懂整个netty流程； bootstrapServer -> NioEventLoopGroup(bossGroup) -> Acceptor(workGroup) ->NioSocketChannel
//        ->NioEventLoop -执行；
//        回调函数，function，等待时机成熟，就会触发；
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(5);
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf =(ByteBuf)msg;
                                System.out.println("receive msg from clinet: "+ byteBuf.toString(StandardCharsets.UTF_8));
                                ctx.writeAndFlush(Unpooled.wrappedBuffer("I am Server".getBytes(StandardCharsets.UTF_8)));
                                super.channelRead(ctx, msg);
                            }
//                        }).addLast(new ChannelOutboundHandlerAdapter(){
//                            @Override
//                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//                                super.write(ctx, msg, promise);
//                            }
                        });
                    }
                });
        serverBootstrap.bind(new InetSocketAddress("localhost",8080));
        serverBootstrap.bind(new InetSocketAddress("localhost",8090));

    }
}
