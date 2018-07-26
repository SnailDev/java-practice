package com.snaildev;

public class HelloInstanceFactory {
    public IHello getInstance(String msg) {
        return new HelloImpl(msg);
    }
}
