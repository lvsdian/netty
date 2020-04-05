package cn.andios.netty.fourth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/05/21:38
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 根据读空闲还是写空闲还是读写空间打印对应信息
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                default:
                    eventType = "impossible";
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件" + eventType);
            ctx.channel().close();
        }
    }
}
