package ubi.data;

import ubi.data.transformers.TransformerRegister;

public class DataFrame {
    private final TransformerRegister register;

    public DataFrame() {
        register = TransformerRegister.getInstance();
    }

    public Column getCol(String name) {
        return new Column(name, this);
    }


    private boolean hasTransformer(String name) {
        return false;
    }

    ;
}
