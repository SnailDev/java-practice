package com.snaildev;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class HelloTest {
    @Test
    public void testSayHello() {
        ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
        IHello hello = context.getBean("hello", IHello.class);
        hello.sayHello();
    }

    @Test
    public void testSayHello1() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello1.xml");
        IHello hello = beanFactory.getBean(IHello.class);
        hello.sayHello();
    }

    @Test
    public void testSayHello2() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello2.xml");
        IHello hello = beanFactory.getBean("bean", IHello.class);
        hello.sayHello();
    }

    @Test
    public void testSayHello3() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello3.xml");

        IHello hello = beanFactory.getBean("hello", IHello.class);
        hello.sayHello();

        IHello hello1 = beanFactory.getBean("alias", IHello.class);
        hello1.sayHello();

        IHello hello2 = beanFactory.getBean("bean", IHello.class);
        hello2.sayHello();

        String[] beanAlias = beanFactory.getAliases("bean");
        Assert.assertEquals(0, beanAlias.length);
    }

    @Test
    public void testSayHello4() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello4.xml");

        sayHello(beanFactory, "hello");
        sayHello(beanFactory, "alias");
        sayHello(beanFactory, "alias1");
        sayHello(beanFactory, "alias6");
        sayHello(beanFactory, "alias8");

        for (String name :
                beanFactory.getAliases("hello")) {
            System.out.println(name);
        }
    }

    public void sayHello(BeanFactory beanFactory, String name) {
        IHello hello = beanFactory.getBean(name, IHello.class);
        hello.sayHello();
    }

    /**
     * 使用构造器实例化Bean
     */
    @Test
    public void testSayHello5() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello5.xml");

        sayHello(beanFactory,"hello");
        sayHello(beanFactory,"hello1");
    }

    /**
     * 使用静态工厂方式实例化Bean
     */
    @Test
    public void testSayHello6() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello6.xml");

        sayHello(beanFactory,"hello");
        sayHello(beanFactory,"hello1");
        sayHello(beanFactory,"helloByType");
        sayHello(beanFactory,"helloByName");
    }

    /**
     * 使用实例工厂方法实例化Bean
     */
    @Test
    public void testSayHello7() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello7.xml");

        sayHello(beanFactory,"hello");
        sayHello(beanFactory,"hello1");
        sayHello(beanFactory,"helloByType");
        sayHello(beanFactory,"helloByName");
    }

    /**
     * 构造器注入
     */
    @Test
    public void testSayHello8() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello8.xml");

        sayHello(beanFactory,"byIndex");
        sayHello(beanFactory,"byType");
        sayHello(beanFactory,"byName");
    }

    /**
     * setter注入 方法名需要遵循约定
     */
    @Test
    public void testSayHello9() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello9.xml");

        sayHello(beanFactory,"bean");
    }

    @Test
    public void testSayHello10() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello10.xml");

        sayHello(beanFactory,"idrefbean");
    }
}