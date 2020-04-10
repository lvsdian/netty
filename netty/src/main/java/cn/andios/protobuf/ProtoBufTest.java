package cn.andios.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/10/16:17
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //对象一
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三")
                .setAge(20)
                .setAddress("北京")
                .build();
        //转为字节数组(可以在网络中进行传输)
        byte[] student2ByteArray = student.toByteArray();

        //对象二(字节数组也可以转为Python或其他语言的对象，)
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2.toString());
        System.out.println(student2.getName());
        System.out.println(student2.getAddress());
        System.out.println(student2.getAge());
    }
}
