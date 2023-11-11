package org.example.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequestMsg) throws Exception {
        String msg = String.format("receive http request: url: %s, method: %s, content : %s %n",
                httpRequestMsg.uri(), httpRequestMsg.method(), httpRequestMsg.content().toString(StandardCharsets.UTF_8));
        System.out.println(msg);
        String content = "hello, i am server";
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
