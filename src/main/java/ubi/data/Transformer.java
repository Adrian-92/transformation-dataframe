package ubi.data;

import ubi.operations.Operation;

public class Transformer {

    private final DataFrame df;

    private String name;
    private int id;
    // deep copy of last state before adding this
    private final ReferenceMap references;
    private final Operation f;

    public Transformer(DataFrame df, Operation f, String name) {
        this.df = df;
        this.name = name;
        this.references = df.cloneTransformerMap();
        this.f = f;
        TransformerRegister.add(this);
        this.id = TransformerRegister.id;
        System.out.println("id in transformer: " + name + " is:" + id);
    }

    void eval(LazyRow row) {
    }

    int getTransId(String name) {
        return references.getData().get(name);
    }

    void setLambda(Operation op) {

    }

    public Operation getF() {
        return f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set name in transformer: " + name);
    }
}
