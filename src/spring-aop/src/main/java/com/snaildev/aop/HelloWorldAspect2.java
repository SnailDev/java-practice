package com.snaildev.aop;

import com.snaildev.service.IIntroductionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.DeclareParents;
import sun.awt.SunHints;

@Aspect
public class HelloWorldAspect2 {
    @DeclareParents(value = "com.snaildev.service.IHelloService+", defaultImpl = com.snaildev.service.impl.IntroductionService.class)
    private IIntroductionService introductionService;

    @Pointcut(value = "execution(* com.snaildev..*.sayBefore(..)) && args(param)", argNames = "param")
    public void beforePointcut(String param) {
    }

    @Before(value = "beforePointcut(param)", argNames = "param")
    public void beforeAdvice(String param) {
        System.out.println("=========before advice param:" + param);
    }

    @AfterReturning(
            value = "execution(* com.snaildev..*.sayBefore(..))",
            pointcut = "execution(* com.snaildev..*.sayAfterReturning(..))",
            argNames = "retVal", returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("===========after returning advice retVal:" + retVal);
    }

    @AfterThrowing(
            value = "execution(* com.snaildev..*.sayAfterThrowing(..))",
            argNames = "exception", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("===========after throwing advice exception:" + exception);
    }

    @After(value = "execution(* com.snaildev..*.sayAfterFinally(..))")
    public void afterFinallyAdvice() {
        System.out.println("===========after finally advice");
    }

    @Around(value = "execution(* com.snaildev..*.sayAround(..))")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
        System.out.println("============around before advice");
        Object retVal = point.proceed(new Object[]{"replace"});
        System.out.println("============around after advice");

        return retVal;
    }
}
