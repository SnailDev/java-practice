package com.snaildev;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectTest {
    @Test
    public void testListInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("listinject.xml");
        ListTestBean listTestBean = beanFactory.getBean("listBean", ListTestBean.class);
        System.out.println(listTestBean.getValues().size());

        Assert.assertEquals(3, listTestBean.getValues().size());
    }

    @Test
    public void testSetInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("listinject.xml");
        ListTestBean listTestBean = beanFactory.getBean("setBean", ListTestBean.class);
        System.out.println(listTestBean.getValues().size());

        Assert.assertEquals(3, listTestBean.getValues().size());
    }

    @Test
    public void testArrayInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("arrayinject.xml");
        ArrayTestBean arrayTestBean = beanFactory.getBean("arrayBean", ArrayTestBean.class);
        System.out.println(arrayTestBean.getNumbers().length);
        System.out.println(arrayTestBean.getLocations().length);
    }

    @Test
    public void testMapInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("mapinject.xml");
        MapTestBean mapTestBean = beanFactory.getBean("mapBean", MapTestBean.class);
        System.out.println(mapTestBean.getValues().containsKey("1"));
    }

    @Test
    public void testBeanInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("hello.xml");
        IHello hello1 = beanFactory.getBean("helloDecorator1", IHello.class);
        hello1.sayHello();
        IHello hello2 = beanFactory.getBean("helloDecorator2", IHello.class);
        hello2.sayHello();
    }

    @Test
    public void testParentInject() {
        ApplicationContext parentBeanContext = new ClassPathXmlApplicationContext("parent.xml");
        ApplicationContext beanContext = new ClassPathXmlApplicationContext(new String[]{"child.xml"}, parentBeanContext);

        IHello hello1 = beanContext.getBean("decorator1", IHello.class);
        hello1.sayHello();
        IHello hello2 = beanContext.getBean("decorator2", IHello.class);
        hello2.sayHello();
    }
}

