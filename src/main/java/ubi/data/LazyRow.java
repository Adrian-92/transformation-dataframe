package ubi.data;

import ubi.operations.Operation;

import java.util.Map;

public class LazyRow {
    private boolean[] evaluated;
    // id, operation
    private Map<Integer, Operation> values;

    public Operation get(int transformerId) {
        if (!values.containsKey(transformerId)) {
            return null;
        }
        return values.get(transformerId);
    }

}
