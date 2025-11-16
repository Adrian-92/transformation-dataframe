package ubi;

import java.util.HashMap;
import java.util.Map;

public class RowNameWrapper {
    Map<String, Integer> references;
    LazyRow row;

    public RowNameWrapper() {
        references = new HashMap<>();
        row = new LazyRow();
    }
    public RowNameWrapper(Map<String, Integer> references, LazyRow row) {
        this.references = references;
        this.row = row;
    }

    Object get(String name) {
        return row.get(references.get(name)); // TODO: apply magic here
    }


}
