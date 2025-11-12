package ubi.data;

import ubi.data.transformers.Transformer;
import ubi.operations.Operation;

import java.util.Map;

public class Column {
    private DataFrame master;
    private String name;

    public Column(String name, DataFrame master) {
        this.master = master;
        this.name = name;
    }


    public void set(Operation function, boolean override) {
        if (!override || !master.hasTransformer(name)) {
            master.setTransformer(name);
        } else {
            new Transformer(master);
        }
        // TODO: Set operation in lambda
    }

}

