package ubi.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a tabular data structure which manages calculation of columns independently.
 * The calculation will be performed lazy and asynchronous and cached per row
 */
public class Dataframe {

    private final Map<String, Integer> nameToIdReferences;
    private final List<Column> columns;

    public Dataframe() {
        nameToIdReferences = new HashMap<>();
        columns = new ArrayList<>();
    }

    /**
     * Initializes a column with given name in its dataframe or retrieves a defines column
     * to define its transformer.
     *
     * @param name Defines the name of the column.
     * @return A {@link Column} which can be processed.
     */
    public Column getCol(String name) {
        Column newCol = new Column(name, this);
        columns.add(newCol);
        return newCol;
    }

    /**
     * Executes the evaluation for n columns across n simulated rows
     * and collects all results.
     * if n is greater than the number of existing columns it will be filled with default values.
     *
     * @param n number of columns and rows.
     * @return nxn-matrix like object structure with all calculated values.
     */
    public List<List<Object>> take(int n) {
        return takeHelper(n, 0);
    }

    /**
     * Executes the evaluation for n columns across n simulated rows
     * and collects all results.
     * if n is greater than the number of existing columns it will be filled with default values.
     *
     * @param n            number of columns and rows.
     * @param defaultValue sets a default entry for missing columns.
     * @return nxn-matrix like object structure with all calculated values.
     */
    public List<List<Object>> take(int n, Object defaultValue) {
        return takeHelper(n, defaultValue);
    }

    Integer getTransformerId(String name) {
        return nameToIdReferences.get(name);
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

    /* ################ Internal helpers ################ */
    // used for overloading default value
    private List<List<Object>> takeHelper(int n, Object defaultValue) {
        List<List<Object>> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i < columns.size()) {
                Column col = columns.get(i);
                results.add(takeNameHelper(col.getName(), n));
            } else {
                List<Object> defaultValues = new ArrayList<>(n);
                for (int row = 0; row < n; row++) {
                    defaultValues.add(defaultValue);
                }
                results.add(defaultValues);
            }
        }
        return results;
    }

    /**
     * Executes the evaluation for a specified column across n simulated rows
     * and collects all results.
     * Each row is calculated independently and uses its own cache.
     * This method blocks until all results are calculated. (using join() from CompletableFuture)
     *
     * @param name The name of the column to evaluate.
     * @param n    The number of rows to process.
     * @return A list containing the result for each of the 'n' rows.
     */
    private List<Object> takeNameHelper(String name, int n) {
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

}