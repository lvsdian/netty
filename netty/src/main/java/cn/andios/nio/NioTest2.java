package cn.andios.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/17/16:57
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        // 反转
        byteBuffer.flip();

        while(byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Character：" + (char)b);
        }
        fileInputStream.close();
    }
}
