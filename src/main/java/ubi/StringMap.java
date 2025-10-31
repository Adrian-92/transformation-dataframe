package ubi;

import java.util.HashMap;

/**
 * mapping of name to id
 */
public final class StringMap {
    private static StringMap INSTANCE;
    private final HashMap<String, Integer> data;

    public synchronized static StringMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StringMap();
        }
        return INSTANCE;
    }

    public StringMap() {
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
