package ubi.transformer;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

class LazyRow {

    private final Map<Integer, CompletableFuture<Object>> values = new ConcurrentHashMap<>();

    CompletableFuture<Object> get(int transId) {
        // computeIfAbsent ensures that every transformer only starts once per row
        return values.computeIfAbsent(transId, id ->
                TransformerRegister.get(id).eval(this)
        );
    }

    boolean has(int transId) {
        return values.containsKey(transId) && values.get(transId).isDone();
    }

}
