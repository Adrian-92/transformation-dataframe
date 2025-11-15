package ubi;

import java.util.Map;
import java.util.Objects;

public class LazyRow {

    Map<Integer, Objects> values;
    boolean[] evaluated;


    Object get(int transId) {
        if (!has(transId)) {
            TransformerRegister.get(transId).eval(this);
        }
        return values.get(transId);
    }

    boolean has(int transId) {
        return values.containsKey(transId);
    }

    void set(int transId, Object value) {}
}
