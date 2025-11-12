package ubi.data;

import ubi.operations.Operation;

import java.util.Map;

public class LazyRow {
    private boolean[] evaluated;
    private Map<Integer, Operation> values;

    public Object get(int transformerId) {
        if (!values.containsKey(transformerId)) {
            // get new transformer from register here
            return null;
        }
        return values.get(transformerId);
    }


    public boolean hasId(int transformerId) {
        return values.containsKey(transformerId);
    }

    void set(Operation operation) {}
}
