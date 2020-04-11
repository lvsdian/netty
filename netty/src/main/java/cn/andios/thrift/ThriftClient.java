package cn.andios.thrift;

import cn.andios.thrift.generated.Person;
import cn.andios.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/11/22:25
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFastFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            tTransport.open();

            Person person = client.getPersonByUserName("张三");
            System.out.println(person);

            System.out.println("-------------------");

            Person p = new Person();
            p.setUsername("李四");
            p.setAge(30);
            p.setMarried(true);

            client.savePerson(p);

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }finally {
            tTransport.close();
        }
    }
}
