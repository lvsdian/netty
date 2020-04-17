package cn.andios.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/17/16:28
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);
        }

        System.out.println("before flip limit:" + intBuffer.limit());

        intBuffer.flip();

        System.out.println("after flip limit：" + intBuffer.limit());
        System.out.println("enter while loop");

        while (intBuffer.hasRemaining()){
            System.out.println("position：" + intBuffer.position());
            System.out.println("limit：" + intBuffer.limit());
            System.out.println("capacity：" + intBuffer.capacity());
            System.out.println("get ele：" + intBuffer.get());
            System.out.println("====================");
        }
    }
}
