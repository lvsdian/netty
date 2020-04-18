package cn.andios.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/14:08
 */
public class NioTest8 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        // 分配一个direct buffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        while (true){

            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);
            if(-1 == read){
                break;
            }

            System.out.println("read：" + read);

            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        outputChannel.close();
        inputChannel.close();

    }
}
