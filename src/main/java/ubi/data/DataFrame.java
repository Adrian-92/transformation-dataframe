package ubi.data;

import ubi.data.transformers.OperationMap;
import ubi.data.transformers.ReferenceMap;
import ubi.data.transformers.TransformerRegister;

public class DataFrame {
    final TransformerRegister register;

    public DataFrame() {
        register = TransformerRegister.getInstance();
    }

    public void getCol(String name) {

        Column col = new Column(name, this);

    }


    boolean hasTransformer(String name) {
        return false;
    }

    void setTransformer(String name) {
        ReferenceMap refMap = register.getRefMap();
        OperationMap operationMap = register.getOperationMap();
    }


}
