package ubi.data;

public class DataFrame {

    private final ReferenceMap refMap;

    public DataFrame() {
        refMap = ReferenceMap.getInstance();
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
        getTransformer(name).setName(name);
    }

    Transformer getTransformer(String name) {
        return TransformerRegister.getTransformer(name);
    }


    ReferenceMap cloneTransformerMap() {
        ReferenceMap deepCopy = new ReferenceMap();
        for (String key : refMap.getData().keySet()) {
            deepCopy.put(key, refMap.get(key));
        }
        return deepCopy;

    }

}
