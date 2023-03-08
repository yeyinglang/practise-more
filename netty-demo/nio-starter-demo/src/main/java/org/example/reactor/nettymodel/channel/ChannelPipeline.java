package org.example.reactor.nettymodel.channel;

import java.util.ArrayList;
import java.util.List;

public interface ChannelPipeline {
     void fireChannelRead(String msg);

    ChannelPipeline addLast(ChannelHandler channelHandler);
}
