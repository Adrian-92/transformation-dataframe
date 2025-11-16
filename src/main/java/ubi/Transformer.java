package ubi;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Transformer {

    Function function;
    private final int id;
    private final Map<String, Integer> references;

    public Transformer(Dataframe df, Function f) {
        this.references = df.copyReferences();
        this.function = f;
        this.id = TransformerRegister.nextTransformerId();
        TransformerRegister.add(this);
    }

    public CompletableFuture<Object> eval(LazyRow row) {
        if (row.has(getTransId())) {
            // Cache hit: returns the cached value immediately
            return CompletableFuture.completedFuture(row.get(getTransId()));
        }
        // Cache miss: executes the function, which handles fetching dependencies
        CompletableFuture<Object> resultFuture = function.execute(new RowNameWrapper(references, row));
        // When the calculation is complete, cache the result and return it
        return resultFuture.thenApply(value -> {
            row.set(getTransId(), value);
            return value;
        });
    }

    public int getTransId() {
        return this.id;
    }

}