package org.example.reactor.nettymodel.channel;

public class ChannelInitializer implements ChannelHandler{

    @Override
    public void channelRead(String msg) {
        System.out.println("hello channel initializer in channalRead");
    }

    @Override
    public void channelRegister(ChannelContext context) {
        ChannelHandler.super.channelRegister(context);
    }

    public void initChannel(NioSocketChannel socketChannel) {
        System.out.println("init initializer");
    }
}
