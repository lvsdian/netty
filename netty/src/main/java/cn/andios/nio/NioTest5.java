package cn.andios.nio;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/13:02
 */
public class NioTest5 {
    /**
     * ByteBuffer可以存放多种类型数据
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putInt(5);
        byteBuffer.putLong(50000000000000L);
        byteBuffer.putFloat(0.55f);
        byteBuffer.putDouble(0.5555);
        byteBuffer.putChar('你');

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getFloat());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
    }
}
