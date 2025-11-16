package ubi;

import ubi.lambda.Lambda_One;

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

    void setTransformer(String name, Lambda_One lambda) {
        // TODO: ask what this should do
        int id = nameToIdReferences.get(name);
        Transformer t = TransformerRegister.get(id);
        t.setLambda(lambda);
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
