package cn.andios.nio;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/13:28
 */
public class NioTest7 {
    /**
     * 可以随时将一个普通buffer调用asReadOnlyBuffer()转为一个只读buffer
     * 但不能将一个只读buffer转为读写buffer
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // class java.nio.HeapByteBuffer
        System.out.println(byteBuffer.getClass());

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        // class java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());

    }
}
