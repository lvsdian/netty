package cn.andios.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/11:36
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        // 1.根据FileInputStream对象获取FileChannel对象
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        // 2.创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            // 上面分配byteBuffer大小为 1024，初始时position = 0，limit = capacity = 1024，
            // 假设读到的数据是100，
            // inputChannel.read()调用后，read = 100，position = 100,
            // flip()之后，position = 0,limit = 100
            // outputChannel.write()调用后，position = limit = 100
            // 如果调用了clear()
            //      position = 0，limit = 1024，
            //      再去读，没有数据了，read = -1，就退出来了
            // 如果没有调用clear()
            //      position = limit = 100
            //      再去读，因为position = limit，所以不可能再去读了，所以read = 0
            //      read = 0没有跳出循环，调用flip()，position = 0，limit = 100
            //      outputChannel.write()调用后，数据又从buffer输出
            //      一直循环写下去
            byteBuffer.clear();

            // 3.将数据从channel输入到buffer
            int read = inputChannel.read(byteBuffer);
            if(-1 == read){
                break;
            }
            byteBuffer.flip();
            // 4.将数据从byteBuffer输出到channel
            outputChannel.write(byteBuffer);
        }
        outputChannel.close();
        inputChannel.close();

    }
}
