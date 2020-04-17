package cn.andios.decorator;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/17/16:11
 */
public class Decorator implements Component {

    Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
