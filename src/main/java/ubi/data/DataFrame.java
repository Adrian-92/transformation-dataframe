package ubi.data;

public class DataFrame {

    private final TransformerRegister register;
    private final OperationMap operationMap;
    private final ReferenceMap refMap;

    public DataFrame() {
        register = TransformerRegister.getInstance();
        operationMap = OperationMap.getInstance();
        refMap = new ReferenceMap();
    }

    // this one will be visible from outside
    public Column getCol(String name) {
        return new Column(name, this);
    }



    /* ########################################################################### */

    // TODO: from here not visible from outside (later)
    boolean hasTransformer(String name) {
        return refMap.getData().get(name) != null;
    }

    void setTransformer(String name) {
    }

    Transformer getTransformer(String name) {
        return null;
    }

    ReferenceMap cloneTransformerMap() {
        ReferenceMap deepCopy = new ReferenceMap();
        for (String key : refMap.getData().keySet()) {
            deepCopy.put(key, refMap.get(key));
        }
        return deepCopy;

    }

}
