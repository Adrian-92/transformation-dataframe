package ubi.data;

import ubi.operations.Operation;

public class Column {
    private DataFrame df;
    private String name;

    public Column(String name, DataFrame df) {
        this.df = df;
        this.name = name;
    }

    public void set(Operation function, boolean override) {
        setHelper(function, override);
    }

    // default value is false for override. change it here if necessary
    public void set(Operation function) {
        setHelper(function, false);
    }

    private void setHelper(Operation function, boolean override) {
        if (override || df.hasTransformer(name)) {
            df.setTransformer(name);
        } else {
            new Transformer(df, function, name);
        }
        df.getTransformer(name).setLambda(function);
    }

}

