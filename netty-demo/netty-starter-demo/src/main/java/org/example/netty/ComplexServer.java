package org.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.example.utils.PrintUtils.printMsg;

/**
 * 1 bossGroup、workGroup -- threadpool
 * 2 channel
 * 3 channelHandler
 * 4 pipLine
 * 5 context
 *
 */
public class ComplexServer {
    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        final DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder()).addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf)msg;
                                printMsg(Thread.currentThread().getName() + ",output >>> " + byteBuf.toString(StandardCharsets.UTF_8));
//                                output
                                ctx.writeAndFlush(Unpooled.wrappedBuffer("hello client,I am Server".getBytes(StandardCharsets.UTF_8)));
                                ctx.fireChannelRead(msg);
                            }
                        }).addLast(defaultEventLoopGroup, new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("eee");
                                defaultEventLoopGroup.execute(()->{
                                    try {
                                        TimeUnit.SECONDS.sleep(10);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println("hello world");
                                });
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind(8090);
        System.out.println(System.currentTimeMillis());
        System.out.println(channelFuture.isDone());
        channelFuture.addListener((x)->{
            System.out.println(System.currentTimeMillis());
            System.out.println("connected");
            System.out.println(x);
        });

    }
}
