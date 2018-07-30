package com.snaildev.pojo;

import org.springframework.beans.factory.annotation.Value;

public class SpELBean {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Value("#{'Hello' + world}")
    private String value;
}
