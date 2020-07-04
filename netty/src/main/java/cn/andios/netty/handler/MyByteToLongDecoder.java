package cn.andios.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description:
 * @author:LSD
 * @when:2020/06/30/21:57
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoked");

        System.out.println(in.readableBytes());

        // 判断缓冲区中的数据是否足够
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
