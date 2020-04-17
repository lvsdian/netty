package cn.andios.decorator;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/17/16:10
 */
public class ConcreteComponent implements Component{
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
