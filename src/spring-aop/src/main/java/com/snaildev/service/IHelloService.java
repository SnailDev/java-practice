package com.snaildev.service;

public interface IHelloService {
    public void sayHello();

    public void sayBefore(String param);

    public boolean sayAfterReturning();

    public void sayAfterThrowing();

    public void sayAfterFinally();

    public void sayAround(String param);

    public void sayAdvisorBefore(String param);
}
