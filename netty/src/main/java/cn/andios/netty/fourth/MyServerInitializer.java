package cn.andios.netty.fourth;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/05/21:34
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //Initializer中添加两个handler
        ChannelPipeline pipeline = ch.pipeline();
        //空闲状态检测的处理器
        pipeline.addLast(new IdleStateHandler(5,7,10,TimeUnit.SECONDS));
        //自定义处理器
        pipeline.addLast(new MyServerHandler());
    }
}
