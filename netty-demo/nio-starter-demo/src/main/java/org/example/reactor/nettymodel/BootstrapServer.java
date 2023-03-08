package org.example.reactor.nettymodel;

import org.example.reactor.nettymodel.channel.ChannelHandler;
import org.example.reactor.nettymodel.channel.ChannelInitializer;
import org.example.reactor.nettymodel.channel.NioSocketChannel;

/**
 * 注册Acceptor和subReactor线程
 * 注册ChannelContext、ChannelPipeline
 * 注册ChannelHandler
 *
 * ChannelPipeline 提供一个责任链的功能； next方法
 * ChannelHandler 提供处理流程
 * ChannelContext 包装ChannelHandler；--- 调用处；
 */
public class BootstrapServer {
    private int workCount;
    private ChannelHandler child;

    public static void main(String[] args) {
        BootstrapServer bootstrapServer = new BootstrapServer();
        bootstrapServer.group(10)
                .childHandler(new ChannelInitializer() {
                    public void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new ChannelHandler() {
                            @Override
                            public void channelRead(String msg) {
                                System.out.println("hander 1 to deal with msg: "+ msg);
                            }
                        }).addLast(new ChannelHandler() {
                            @Override
                            public void channelRead(String msg) {
                                System.out.println("hander 2 to deal with msg: "+ msg);
                            }
                        }).addLast(new ChannelHandler() {
                            @Override
                            public void channelRead(String msg) {
                                System.out.println("hander 3 to deal with msg: "+ msg);
                            }
                        });
                    }
                });
        bootstrapServer.bind();
    }

    public BootstrapServer group(int bossWorkCount) {
        this.workCount= bossWorkCount;
        return this;
    }

    public BootstrapServer childHandler(ChannelHandler channelHandler){
        this.child = channelHandler;
        return this;
    }

    public void bind(){
        try(Reactor reactor = new Reactor(this.workCount,this.child)) {
            reactor.run();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
