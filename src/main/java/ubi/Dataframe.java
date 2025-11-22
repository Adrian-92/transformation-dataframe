package ubi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * here should be a vast explanation what this thing does
 */
public class Dataframe {

    private final Map<String, Integer> nameToIdReferences;

    public Dataframe() {
        nameToIdReferences = new HashMap<>();
    }

    /**
     * Initializes a column with given name in its dataframe.
     *
     * @param name defines the name of the column.
     * @return returns column which can be processed.
     */
    public Column getCol(String name) {
        return new Column(name, this);
    }

    /**
     * Executes the evaluation for a specified column across n simulated rows
     * and collects all results.
     *
     * @param name The name of the column to evaluate.
     * @param n    The number of rows to process.
     * @return A list containing the result (Object) for each of the 'n' rows.
     */
    public List<Object> take(String name, int n) {
        // maybe just init as null?
        // this is to
        List<Object> results = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            // each row must instantiate a new lazy row
            // this is kind of caching
            LazyRow currentRow = new LazyRow();

            RowNameWrapper rowWrapper = new RowNameWrapper(copyReferences(), currentRow);

            CompletableFuture<Object> resultFuture = rowWrapper.get(name);
            Object result = resultFuture.join();
            results.add(result);
        }
        return results;
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