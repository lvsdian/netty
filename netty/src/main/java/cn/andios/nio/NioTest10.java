package cn.andios.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/21:48
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        // 从哪个位置开始锁，锁多长，是否为共享锁
        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("lock：" + fileLock.isValid());
        System.out.println("is shared：" + fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();
    }
}
