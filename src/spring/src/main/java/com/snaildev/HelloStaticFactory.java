package com.snaildev;

public class HelloStaticFactory {
    public static IHello getInstance(String msg) {
        return new HelloImpl(msg);
    }
}
