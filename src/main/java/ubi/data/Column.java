package ubi.data;

import java.util.Map;

public class Column {
    private DataFrame master;
    private String name;

    public Column(String name, DataFrame master) {
        this.master = master;
        this.name = name;
    }

    public void set(Map<Map<String, Object>, Object> function, boolean override) {
        if (!override || !master.hasTransformer(name)) {

        }

    }

}

