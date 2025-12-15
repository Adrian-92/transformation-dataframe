package ubi.transformer;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

class RowNameWrapper {
    private final Map<String, Integer> references;
    private final LazyRow row;


    RowNameWrapper(Map<String, Integer> references, LazyRow row) {
        this.references = references;
        this.row = row;
    }

    CompletableFuture<Object> get(String name) {
        Integer transId = references.get(name);
        if (transId == null) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Column not found: " + name));
        }
        return row.get(transId);
    }

    int getRowIndex() {
        return row.getRowIndex();
    }
}