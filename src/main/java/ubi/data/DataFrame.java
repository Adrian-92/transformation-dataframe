package ubi.data;

public class DataFrame {
    // TODO: hide id in package (later, but important!)
    public static int id;
    private final TransformerRegister register;
    private final OperationMap operationMap;
    private final ReferenceMap refMap;

    public DataFrame() {
        register = TransformerRegister.getInstance();
        operationMap = OperationMap.getInstance();
        refMap = new ReferenceMap();
    }

    public Column getCol(String name) {
        return new Column(name, this);
    }

    boolean hasTransformer(String name) {
        return false;
    }

    void setTransformer(String name) {
    }

    Transformer getTransformer(String name) {
        return null;
    }


}
