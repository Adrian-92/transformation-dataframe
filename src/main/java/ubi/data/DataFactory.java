package ubi.data;

import ubi.operations.Operation;

/**
 * takes name and operation and maps to (counter-)id
 */
public class DataFactory {
    private static int counter;

    private final OperationMap operationMap;
    private final StringMap stringMap;

    public DataFactory() {
        operationMap = OperationMap.getInstance();
        stringMap = StringMap.getInstance();
    }

    /**
     * reserves ids for functions
     *
     * @param n number of reserved space
     */
    public void take(int n) {
        // shouldn't this take only generated functions?
        int temp = counter;
        for (int i = temp; i < n + temp; i++) {
            operationMap.put(i, null);
            counter++;
        }
    }

    public void apply(String name, Operation op) {
        if (stringMap.get(name) != null) {
            // here you would set a new name
            // maybe there needs to be a separate function?
            throw new RuntimeException("Duplicate key");
        }
        int temp = counter;
        for (int i = 0; i < temp + 1; i++) {
            if (operationMap.get(i) != null) {
                continue;
            }
            stringMap.put(name, i);
            operationMap.put(i, op);
            counter++;
            break;
        }

    }

    public Operation getCol(String name) {
        Integer key = stringMap.get(name);
        if (key == null) {
            throw new RuntimeException("No such column: " + name);
        }
        Operation operation = operationMap.get(key);
        if (operation == null) {
            throw new RuntimeException("No such operation: " + name);
        }
        return operation;
    }

    public StringMap getStringMap() {
        return stringMap;
    }

    public static int getCounter() {
        return counter;
    }
}
