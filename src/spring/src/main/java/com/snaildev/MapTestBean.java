package com.snaildev;

import java.util.Map;
import java.util.Properties;

public class MapTestBean {
    private Map<String, String> values;
    private Properties values1;

    public Properties getValues1() {
        return values1;
    }

    public void setValues1(Properties values1) {
        this.values1 = values1;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
