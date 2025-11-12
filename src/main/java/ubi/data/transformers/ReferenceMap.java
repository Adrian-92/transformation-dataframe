package ubi.data.transformers;

import java.util.HashMap;

/**
 * mapping of name to id
 */
public class ReferenceMap {
    private final HashMap<String, Integer> data;


    ReferenceMap() {
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
