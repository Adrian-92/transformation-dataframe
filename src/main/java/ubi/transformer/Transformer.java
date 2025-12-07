package ubi.transformer;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

class Transformer {

    private final Function function;
    private final int id;
    private final Map<String, Integer> references;

    Transformer(Dataframe df, Function f) {
        this.references = df.copyReferences();
        this.function = f;
        this.id = TransformerRegister.nextTransformerId();
        TransformerRegister.add(this);
    }

    // be cautious with this one
    Transformer(Dataframe df, Function f, int id) {
        this.references = df.copyReferences();
        this.function = f;
        this.id = id;
        TransformerRegister.add(this);

    }

    CompletableFuture<Object> eval(LazyRow row) {
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

    int getTransId() {
        return this.id;
    }

}