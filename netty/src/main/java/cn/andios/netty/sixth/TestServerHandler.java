package cn.andios.netty.sixth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/10/16:42
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if(dataType == MyDataInfo.MyMessage.DataType.PersonType){
            System.out.println(msg.getPerson().toString());
        }else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
            System.out.println(msg.getDog().toString());
        }else{
            System.out.println(msg.getCat().toString());
        }
    }
}
