package ubi.data;

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
    // important: use this only for deep copy of list!
    public ReferenceMap() {
        this.data = new HashMap<>();
    }


    // important: use this only for deep copy of list
    public void put(String key, Integer id) {
        this.data.put(key, id);
    }

    // use this when adding new transformer
    public void put(String key) {
        this.data.put(key, ++TransformerRegister.id);
    }


    public HashMap<String, Integer> getData() {
        return data;
    }

    public Integer get(String key) {
        return this.data.get(key);
    }
}
