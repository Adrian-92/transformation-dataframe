package ubi.data.transformers;

import java.util.HashMap;

/**
 * mapping of name to id
 */
public final class ReferenceMap {
    private static ReferenceMap INSTANCE;
    private final HashMap<String, Integer> data;

    public synchronized static ReferenceMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReferenceMap();
        }
        return INSTANCE;
    }

    private ReferenceMap() {
        this.data = new HashMap<>();
    }

    public void put(String key, Integer id) {
        this.data.put(key, id);
    }

    public HashMap<String, Integer> getData() {
        return data;
    }

    public Integer get(String key) {
        return this.data.get(key);
    }
}
