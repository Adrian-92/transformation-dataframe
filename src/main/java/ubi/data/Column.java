package ubi.data;

import ubi.operations.Operation;

public class Column {
    private DataFrame master;
    private String name;

    public Column(String name, DataFrame master) {
        this.master = master;
        this.name = name;
    }

    private void set(Operation function, boolean override) {
        setHelper(function, override);
    }

    // default value is false for override. change it here if necessary
    private void set(Operation function) {
        setHelper(function, false);
    }

    private void setHelper(Operation function, boolean override) {
        if (!override || !master.hasTransformer(name)) {
            master.setTransformer(name);
        } else {
            new Transformer(master);

        }
        // TODO: Set operation in lambda
    }

}

