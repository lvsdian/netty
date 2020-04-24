package cn.andios.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/24/23:26
 */
public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8899);

        String fileName = "F:\\BaiduNetdiskDownload\\牛客算法\\直通BAT算法精讲\\1.1二叉树打印.flv";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0 ){
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送数据总字节数：" + total +"，耗时：" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
