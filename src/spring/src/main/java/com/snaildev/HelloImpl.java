package com.snaildev;

import java.beans.ConstructorProperties;

public class HelloImpl implements IHello {
    private String msg;
    private boolean success;

    public HelloImpl() {
        this.msg = "Hello World";
    }

   // @ConstructorProperties({"message"})
    public HelloImpl(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public void sayHello() {
        System.out.println(msg);
    }
}
