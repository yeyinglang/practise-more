package org.example.netty.pipline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap= new ServerBootstrap();
        NioEventLoopGroup boss =new NioEventLoopGroup();
        NioEventLoopGroup worker= new NioEventLoopGroup();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .group(boss,worker)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SampleInBoundHandler())
                                .addLast(new Sample2InBoundHandler())
                                .addLast(new Sample3InBoundHandler())
                                .addLast(new SampleoutBoundHandler())
                                .addLast(new Sample2outBoundHandler())
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf byteBuf = (ByteBuf)msg;
                                        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
                                        ctx.writeAndFlush(Unpooled.wrappedBuffer("hello world".getBytes(StandardCharsets.UTF_8)));
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast(new Sample3outBoundHandler());
//                        这个是为了测试异常处理的作用的；
//                                .addLast(new SampleExceptionHandler());

                    }
                });
        serverBootstrap.bind(8080);

    }
}
