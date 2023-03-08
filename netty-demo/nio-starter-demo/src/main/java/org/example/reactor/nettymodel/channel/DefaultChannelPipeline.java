package org.example.reactor.nettymodel.channel;

public class DefaultChannelPipeline implements ChannelPipeline{
    private ChannelContext head ;

    private ChannelContext cur;

    public DefaultChannelPipeline(){
        // 暂时没有tail；
        this.head = new ChannelContext.HeadContext();
        this.cur = head;
    }

    public ChannelPipeline addLast(ChannelHandler channelHandler) {
        ChannelContext channelContext = newContext(channelHandler);
        cur.next = channelContext;
        channelContext.prev = cur;
        cur = channelContext;
        return this;
    }

    public ChannelContext newContext(ChannelHandler channelHandler) {
        return new ChannelContext(channelHandler);
    }

    @Override
    public void fireChannelRead(String msg) {
        head.channelRead(msg,this);
    }

}
