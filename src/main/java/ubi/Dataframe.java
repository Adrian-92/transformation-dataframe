package ubi;

import java.util.HashMap;
import java.util.Map;

public class Dataframe {

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

    Map<String, Integer> copyReferences() {
        return new HashMap<>(); // TODO: apply magic here
    }


}
