package cn.andios.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/20/21:35
 */
public class TestNioClient {
    public static void main(String[] args) throws IOException {
        try {
            // 1.获取channel
            SocketChannel socketChannel = SocketChannel.open();
            // 配置非阻塞
            socketChannel.configureBlocking(false);

            // 2.获取selector
            Selector selector = Selector.open();
            // 3.将channel注册到selector上
            socketChannel.register(selector,SelectionKey.OP_CONNECT);

            // 4.连接
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for(SelectionKey selectionKey:selectionKeys){
                    if(selectionKey.isConnectable()){
                        // 根据selectionKey获取channel
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        // 连接是否处于进行状态
                        if(client.isConnectionPending()){
                            // 完成连接
                            client.finishConnect();

                            // 发数据
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            // 反转
                            writeBuffer.flip();
                            client.write(writeBuffer);

                            // 另起线程，不断读取输入内容
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.submit(()->{
                               while (true){
                                   try {
                                       writeBuffer.clear();
                                       InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                       BufferedReader br = new BufferedReader(inputStreamReader);

                                       // 获取用户数据
                                       String sendMessage = br.readLine();

                                       writeBuffer.put(sendMessage.getBytes());
                                       writeBuffer.flip();

                                       //数据写入服务端
                                       client.write(writeBuffer);
                                   }catch (Exception e){
                                       e.printStackTrace();
                                   }
                               }
                            });
                        }
                        // 注册读
                        client.register(selector,SelectionKey.OP_READ);
                    // 读取
                    }else if(selectionKey.isReadable()){
                        // 根据selectionKey获取channel
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        //从channel中获取数据
                        int count = client.read(readBuffer);
                        if(count > 0){
                            String receiveMessage = new String(readBuffer.array());
                            System.out.println(receiveMessage);
                        }
                    }
                }
                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
