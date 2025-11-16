package ubi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RowNameWrapper {
    private Map<String, Integer> references;
    private LazyRow row;

    public RowNameWrapper() {
        references = new HashMap<>();
        row = new LazyRow();
    }

    public RowNameWrapper(Map<String, Integer> references, LazyRow row) {
        this.references = references;
        this.row = row;
    }

    public CompletableFuture<Object> get(String name) {
        Integer transId = references.get(name);
        if (transId == null) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Column not found: " + name));
        }
        return row.get(transId);
    }
}