package cn.andios.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/20/20:26
 */
public class TestNioServer {

    /** 维护客户端连接信息 */
    private static Map<String,SocketChannel> clientMap = new HashMap<>();


    public static void main(String[] args) throws IOException {
        // 1.获取channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 非阻塞channel
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        // 2.获取selector
        Selector selector = Selector.open();

        // 3.将channel注册到selector上，这个serverSocketChannel关注连接
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        // 客户端发起连接
                        if(selectionKey.isAcceptable()){
                            // 根据selectionKey获取客户端连接的channel，这里是ServerSocketChannel
                            // 前面注册的是ServerSocketChannel，所以这里可以直接强转为ServerSocketChannel
                            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                            // 这个SocketChannel关注数据读取
                            client = channel.accept();
                            client.configureBlocking(false);
                            // 4.前面已经建立连接了，这里关注读取
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "【" + UUID.randomUUID().toString() + "】";
                            clientMap.put(key,client);
                        //是否有数据可读
                        }else if(selectionKey.isReadable()){
                            // 根据selectionKey获取channel，这里是SocketChannel
                            client = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            // 获取数据
                            int counts = client.read(readBuffer);
                            if(counts > 0){
                                // 反转buffer
                                readBuffer.flip();

                                // 打印数据
                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + "client：" + receiveMessage);

                                String senderKey = null;
                                // 遍历map，获取当前client的key
                                for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    if(client == entry.getValue()){
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }
                                // 将receiveMessage写到其他的socket上
                                for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    // 获取其他的SocketChannel
                                    SocketChannel value = entry.getValue();
                                    // 将receiveMessage装入buffer中
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey + "：" + receiveMessage).getBytes());
                                    // 反转buffer
                                    writeBuffer.flip();
                                    // 向其他channel写入buffer里的数据
                                    value.write(writeBuffer);

                                }
                            }
                            // 获取数据、打印、发送给其他客户端(带key)
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
                // 每次处理完都要清除
                selectionKeys.clear();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
