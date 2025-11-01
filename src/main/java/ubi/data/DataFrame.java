package ubi.data;

import ubi.operations.Operation;

import java.util.HashMap;

/**
 * mapping of id to operation
 */
public final class DataFrame {
    private static DataFrame INSTANCE;
    private final HashMap<Integer, Operation> data;

    public synchronized static DataFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataFrame();
        }
        return INSTANCE;
    }

    public DataFrame() {
        this.data = new HashMap<>();
    }

    public void put(int position, Operation op) {
        data.put(position, op);
    }

    public Operation get(int position) {
        return data.get(position);
    }
}
