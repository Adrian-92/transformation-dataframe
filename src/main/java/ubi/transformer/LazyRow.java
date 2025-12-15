package ubi.transformer;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

class LazyRow {
    private final Dataframe df;
    private final int rowIndex;
    private final Map<Integer, CompletableFuture<Object>> values = new ConcurrentHashMap<>();

    LazyRow(Dataframe df, int rowIndex) {
        this.df = df;
        this.rowIndex = rowIndex;
    }

    CompletableFuture<Object> get(int transId) {
        // computeIfAbsent ensures that every transformer only starts once per row
        return values.computeIfAbsent(transId, id ->
                df.get(id).eval(this)
        );
    }

    boolean has(int transId) {
        return values.containsKey(transId) && values.get(transId).isDone();
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
