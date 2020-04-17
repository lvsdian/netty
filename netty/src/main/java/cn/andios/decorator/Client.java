package cn.andios.decorator;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/17/16:14
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();
    }
}
