package org.example.reactor.nettymodel.channel;

@FunctionalInterface
public interface ChannelHandler {
    void channelRead(String msg);

    default void channelRegister(ChannelContext context){
        System.out.println("channel Register finished");
    };

}
