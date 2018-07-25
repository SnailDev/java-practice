package com.snaildev;

import org.junit.Test;
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
}