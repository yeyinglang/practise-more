package org.example.reactor.nettymodel.channel;

import java.nio.channels.SocketChannel;

public class NioSocketChannel {
    private SocketChannel socketChannel;

    private ChannelPipeline channelPipeline;

    public NioSocketChannel(SocketChannel socketChannel, ChannelPipeline channelPipeline) {
        this.socketChannel = socketChannel;
        this.channelPipeline = channelPipeline;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public ChannelPipeline pipeline() {
        return channelPipeline;
    }
}
