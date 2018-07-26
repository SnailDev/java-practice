package com.snaildev;

public class HelloDecorator implements IHello {
    private IHello hello;

    public HelloDecorator() {
    }

    public HelloDecorator(IHello hello) {
        this.hello = hello;
    }

    public IHello getHello() {
        return hello;
    }

    public void setHello(IHello hello) {
        this.hello = hello;
    }

    @Override
    public void sayHello() {
        System.out.println("------装饰一下------");
        hello.sayHello();
        System.out.println("------装饰一下------");
    }
}
