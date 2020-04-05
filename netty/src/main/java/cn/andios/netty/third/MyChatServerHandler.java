package cn.andios.netty.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/05/14:05
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /** 声明一个channelGroup管理channel */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //任何channel连接上来时，channelRead0都会进行处理
        //某一个channel连接上来时，给所有的channel发消息----广播
        Channel channel = ctx.channel();
        //遍历所有的channel
        channelGroup.forEach(ch ->{
            //如果不是自己本身这个channel
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress() + "发送消息：" + msg+ "\n");
            }else{//是本身这个channel
                ch.writeAndFlush("【自己】："+ msg + "\n");
            }
        });
    }

    /**
     * 连接加入
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //有客户端连接上了，就获取这个连接channel
        Channel channel = ctx.channel();

        //将消息写到所有的channelGroup中的channel
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "加入\n");

        //channel添加到channelGroup中
        channelGroup.add(channel);
    }

    /**
     * 连接断开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //有客户端连接断开了，就获取这个断开的channel
        Channel channel = ctx.channel();

        //将消息写到所有的channelGroup中的channel
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "断开\n");

        //这里可以写，也可以不写，channelGroup会自动删掉
        //channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

    /**
     * 异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
