package ubi;

import java.util.HashMap;
import java.util.Map;

public class Dataframe {

    private final Map<String, Integer> nameToIdReferences;

    public Dataframe() {
        nameToIdReferences = new HashMap<>();
    }

    public Column getCol(String name) {
        return new Column(name, this);
    }

    boolean hasTransformer(String name) {
        return nameToIdReferences.containsKey(name);
    }

    void setTransformer(String name) {
        // TODO: ask what this should do
    }

    Transformer getTransformer(String name) {
        return null;
    }



    /**
     * copies map to get a new instance of current state
     *
     * @return shallow copy
     */
    Map<String, Integer> copyReferences() {
        // integer and string are immutable so a shallow copy is suitable
        return new HashMap<>(nameToIdReferences);

    }



}
