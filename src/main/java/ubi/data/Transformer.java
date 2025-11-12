package ubi.data;

import ubi.operations.Operation;

import java.util.Map;

public class Transformer {
    private final DataFrame df;
    // deep copy of last state before adding this
    private final ReferenceMap references;
    private final Operation f;

    public Transformer(DataFrame df, Operation f) {
        this.df = df;
        this.references = df.cloneTransformerMap();
        this.f = f;
        TransformerRegister.add(this);
    }


    void eval(LazyRow row) {
    }

    int getTransId(String name) {
        return 0;
    }

    void setLambda(Operation op) {

    }
}
