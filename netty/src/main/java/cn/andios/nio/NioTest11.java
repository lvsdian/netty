package cn.andios.nio;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/18/21:52
 */
public class NioTest11 {
    /**
     * 关于Buffer的Scattering与Gathering
     * Scattering：将来自一个channel的数据读到多个buffer中，第一个读满了再读第二个...
     * Gathering：将一个buffer数组中的数据写出，先写完第一个，再写第二个...
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        // 总的消息长度
        int messageLen = 2 +3 + 4;
        // buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){
            int bytesRead = 0;
            // 读消息到buffer中
            while (bytesRead < messageLen){
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;

                System.out.println("bytesRead：" + bytesRead);

                Arrays.asList(byteBuffers).stream()
                        .map(buffer->"position：" + buffer.position() + ",limit：" + buffer.limit())
                        .forEach(System.out::println);

            }
            // 翻转buffer
            Arrays.asList(byteBuffers).forEach(buffer->{
                buffer.flip();
            });

            long bytesWritten = 0;
            // 从buffer写出
            while (bytesWritten < messageLen){
                long r = socketChannel.write(byteBuffers);
                bytesWritten += r;
            }
            // 清空
            Arrays.asList(byteBuffers).stream().forEach(buffer->{
                buffer.clear();
            });

            // 验证
            System.out.println("bytesRead：" + bytesRead + ",bytesWritten：" + bytesWritten + "messageLen：" + messageLen);
        }
    }
}
