package cn.andios.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @description:
 * @author:LSD
 * @when:2020/06/07/21:26
 */
public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }

        for (int i = 0; i < buf.capacity(); i++) {
            System.out.println(buf.getByte(i));

        }
    }
}
