package cn.andios.netty.sixth;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/10/16:37
 */
public class TestServer {
    /**
     * TestServerHandler中打印msg信息，可以直接获得TestClientHandler中定义的MyDataInfo.Person对象信息
     *
     * 但这么做有个弊端就是，Handler、Initializer中泛型都是MyDataInfo.Person类型，如果MyDataInfo中
     * 定义了其他message，就无法传输，因为类型已经写死(MyDataInfo.Person)
     *
     * 解决方案：
     *  MyDataInfo.proto中定义MyMessage的一个message，里面指定类型是哪种，Handler、Initializer中的泛型
     *  就可以用MyDataInfo.MyMessage类型
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //服务端添加Initializer
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
