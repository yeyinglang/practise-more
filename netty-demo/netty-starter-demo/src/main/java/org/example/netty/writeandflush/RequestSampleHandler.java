package org.example.netty.writeandflush;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class RequestSampleHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String data = ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
        System.out.println("receive from client: " + data);
        ResponseSample responseSample = new ResponseSample("OK", data, System.currentTimeMillis());
        ctx.channel().writeAndFlush(responseSample);
//        super.channelRead(ctx, responseSample);
    }
}
