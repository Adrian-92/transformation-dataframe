package ubi.data;

import ubi.operations.Operation;

public class Transformer {
    private final DataFrame df;
    // deep copy of last state before adding this
    private final ReferenceMap references;

    public Transformer(DataFrame df) {
        this.df = df;
        this.references = TransformerFactory.cloneMap(TransformerRegister.getInstance());
    }


    void eval(LazyRow row) {
    }

    int getTransId(String name) {
        return references.getData().get(name);
    }

    void setLambda(Operation op) {

    }
}
