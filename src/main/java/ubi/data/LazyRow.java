package ubi.data;

import java.util.Map;

public class LazyRow {
    private boolean[] evaluated;
    private Map<Integer, Object> values;

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

    void set(){}
}
