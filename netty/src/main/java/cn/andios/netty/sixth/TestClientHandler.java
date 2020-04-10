package cn.andios.netty.sixth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/10/16:49
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);

        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("张三")
                .setAge(20)
                .setAddress("南京")
                .build();
        MyDataInfo.Dog dog = MyDataInfo.Dog.newBuilder().
                setName("狗")
                .setAge(2)
                .build();
        MyDataInfo.Cat cat = MyDataInfo.Cat.newBuilder()
                .setName("猫")
                .setCity("上海")
                .build();

        MyDataInfo.MyMessage myMessage = null;

        if(0 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(person)
                    .build();
        }else if(1 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(dog)
                    .build();
        }else{
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(cat)
                    .build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
