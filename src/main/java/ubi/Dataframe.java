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

    void addTransformer(String name, int id) {
        nameToIdReferences.put(name, id);
    }

    Map<String, Integer> copyReferences() {
        // integer and string are immutable so a shallow copy is suitable
        return new HashMap<>(nameToIdReferences);

    }
}