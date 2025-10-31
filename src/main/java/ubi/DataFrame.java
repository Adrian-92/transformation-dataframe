package ubi;

import java.util.HashMap;

/**
 * mapping of id to operation
 */
public class DataFrame {

    private final HashMap<Integer, Operation> data;

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
