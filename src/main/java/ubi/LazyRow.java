package ubi;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class LazyRow {

    // Cache storing computed values
    Map<Integer, Object> values = new ConcurrentHashMap<>();

    // Asynchronously retrieves a column's value by its Transformer ID
    public CompletableFuture<Object> get(int transId) {
        if (has(transId)) {
            // Cache hit: returns immediately completed Future.
            return CompletableFuture.completedFuture(values.get(transId));
        }
        // Cache miss: triggers asynchronous evaluation via the Transformer.
        return TransformerRegister.get(transId).eval(this);
    }

    boolean has(int transId) {
        // Checks if the value is already in the cache.
        return values.containsKey(transId);
    }

    // Saves the result into the cache after successful evaluation.
    void set(int transId, Object value) {
        values.put(transId, value);
    }
}
