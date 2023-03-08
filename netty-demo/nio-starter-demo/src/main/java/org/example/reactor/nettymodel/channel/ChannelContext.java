package org.example.reactor.nettymodel.channel;

/**
 * context 是handler的一层封装，
 */
public class ChannelContext {
    private ChannelHandler channelHandler;

    ChannelContext next;
    ChannelContext prev;

    ChannelContext() {
    }

    ChannelContext(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    void channelRead(String msg, ChannelPipeline channelPipeline) {
        channelHandler.channelRead(msg);
//        ChannelContext nextContext = channelPipeline.next();
        if (next == null) return;
        next.channelRead(msg, channelPipeline);
    }

    void channelRegister(ChannelContext context) {
        channelHandler.channelRegister(context);
    }

    public static class HeadContext extends ChannelContext {


        HeadContext() {
        }

        void channelRead(String msg, ChannelPipeline channelPipeline) {
            if (next == null) return;
            next.channelRead(msg, channelPipeline);
        }
    }
}
