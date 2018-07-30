package com.snaildev;

import com.snaildev.service.IHelloService;
import com.snaildev.service.IIntroductionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void testAop() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService =
                ctx.getBean("helloService", IHelloService.class);
        helloService.sayHello();
    }

    @Test
    public void testSchemaBeforeAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayBefore("before");
        System.out.println("======================================");
    }

    @Test
    public void testSchemaAfterReturningAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayAfterReturning();
        System.out.println("======================================");
    }

    @Test(expected = RuntimeException.class)
    public void testSchemaAfterThrowingAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayAfterThrowing();
        System.out.println("======================================");
    }

    @Test(expected = RuntimeException.class)
    public void testSchemaAfterFinallyAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayAfterFinally();
        System.out.println("======================================");
    }

    @Test
    public void testSchemaAroundAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayAround("haha");
        System.out.println("======================================");
    }

    @Test
    public void testSchemaIntroduction() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        IIntroductionService introductionService = ctx.getBean("helloService", IIntroductionService.class);
        introductionService.introduct();
        System.out.println("======================================");
    }

    @Test
    public void testSchemaAdvisor() {
        System.out.println("======================================");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop_advisor.xml");
        IHelloService helloService = ctx.getBean("helloService", IHelloService.class);
        helloService.sayAdvisorBefore("haha");
        System.out.println("======================================");
    }
}
