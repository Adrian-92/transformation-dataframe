package ubi.data;

import ubi.operations.Operation;

import java.util.HashMap;

/**
 * mapping of id to operation
 */
public final class OperationMap {
    private static OperationMap INSTANCE;
    private final HashMap<Integer, Operation> data;

    public synchronized static OperationMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OperationMap();
        }
        return INSTANCE;
    }

    public OperationMap() {
        this.data = new HashMap<>();
    }


    // TODO: get id from Transformer Register
    public void put(int id, Operation op) {
        data.put(id, op);
    }

    public Operation get(int id) {
        return data.get(id);
    }
}
