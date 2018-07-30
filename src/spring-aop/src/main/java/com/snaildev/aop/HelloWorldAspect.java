package com.snaildev.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class HelloWorldAspect {
    public void beforeAdvice(String param) {
        System.out.println("=========before advice param:" + param);
    }

    public void afterAdvice() {
        System.out.println("=========after advice");
    }

    public void afterReturningAdvice(Object retVal) {
        System.out.println("===========after returning advice retVal:" + retVal);
    }

    public void afterThrowingAdvice(Exception exception) {
        System.out.println("===========after throwing advice exception:" + exception);
    }

    public void afterFinallyAdvice() {
        System.out.println("===========after finally advice");
    }

    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
        System.out.println("============around before advice");
        Object retVal = point.proceed(new Object[]{"replace"});
        System.out.println("============around after advice");

        return retVal;
    }
}
