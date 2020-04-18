package cn.andios.nio;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/13:15
 */
public class NioTest6 {
    /**
     * slice()方法可以截取buffer的一部分作为新的buffer
     * 属于浅拷贝
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte)i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);

        // slice方法根据position、limit截取一部分buffer，position、limit左闭右开
        ByteBuffer sliceBuffer = byteBuffer.slice();

        // 修改sliceBuffer
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get();
            b *= 2;
            sliceBuffer.put(i,b);
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        // 对原来的byteBuffer有影响
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
