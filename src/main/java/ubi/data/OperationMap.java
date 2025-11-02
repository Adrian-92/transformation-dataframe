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

    public void put(int position, Operation op) {
        data.put(position, op);
    }

    public Operation get(int position) {
        return data.get(position);
    }
}
