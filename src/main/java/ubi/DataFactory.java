package ubi;

/**
 * takes name and operation and maps to (counter-)id
 */
public class DataFactory {
    private static int counter;

    private final DataFrame df;
    private final StringMap stringMap;

    public DataFactory() {
        df = DataFrame.getInstance();
        stringMap = StringMap.getInstance();
    }

    /**
     * reserves ids for functions
     *
     * @param n number of reserved space
     */
    public void take(int n) {
        int temp = counter;
        for (int i = temp; i < n + temp; i++) {
            df.put(i, null);
            counter++;
        }
    }

    public void apply(String name, Operation op) {
        if (stringMap.get(name) != null) {
            throw new RuntimeException("Duplicate key");
        }
        int temp = counter;
        for (int i = 0; i < temp + 1; i++) {
            if (df.get(i) != null) {
                continue;
            }
            stringMap.put(name, i);
            df.put(i, op);
            counter++;
            break;
        }

    }

    public Operation getCol(String name) {
        Integer key = stringMap.get(name);
        if (key == null) {
            throw new RuntimeException("No such column: " + name);
        }
        Operation operation = df.get(key);
        if (operation == null) {
            throw new RuntimeException("No such operation: " + name);
        }
        return operation;
    }

    public static int getCounter() {
        return counter;
    }
}
