package cn.andios.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/20/22:17
 */
public class NioTest13 {
    /**
     * unicode：编码方式
     * utf-8：存储方式，utf-8是unicode的实现方式之一
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest13_In.txt";
        String outputFile = "NioTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile,"rw");

        long inputLength = new File(inputFile).length();
        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        // 文件内存映射
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY,0,inputLength);

        System.out.println("==================================");
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println("k：" + "v");
        });
        System.out.println("==================================");


        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        // 解码
        CharBuffer charBuffer = charsetDecoder.decode(inputData);
        // 编码
        ByteBuffer outputData = charsetEncoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();


    }
}
