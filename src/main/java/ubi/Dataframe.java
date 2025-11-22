package ubi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Dataframe {

    private final Map<String, Integer> nameToIdReferences;

    public Dataframe() {
        nameToIdReferences = new HashMap<>();
    }

    public Column getCol(String name) {
        return new Column(name, this);
    }

    /**
     *
     * @param n number if iterations the dataframe should calculate
     * @return resulting Object either as NaN or the (to number) castable object
     *
     */
    public Object take(String name, int n)  {
        // maybe just init as null?
        // this is to
        Object result = Double.NaN;
        for (int i = 1; i <= n; i++) {
            // each row must instantiate a new lazy row
            // this is kind of caching
            LazyRow currentRow = new LazyRow();

            RowNameWrapper rowWrapper = new RowNameWrapper(copyReferences(), currentRow);

            CompletableFuture<Object> resultFuture = rowWrapper.get(name);
            result = resultFuture.join();
        }
        return result;
    }

    boolean hasTransformer(String name) {
        return nameToIdReferences.containsKey(name);
    }

    void addTransformer(String name, int id) {
        nameToIdReferences.put(name, id);
    }

    Map<String, Integer> copyReferences() {
        // integer and string are immutable so a shallow copy is suitable
        return new HashMap<>(nameToIdReferences);

    }
}