package ubi;

/**
 * takes name and operation and maps to (counter-)id
 */
public class DataFactory {
    private static int counter;

    private final DataFrame df = new DataFrame();
    private final StringMap stringMap = StringMap.getInstance();

    public DataFactory() {}

    public void take(int n){}

    public void apply(Operation op){}

    public Operation getCol(String name){
        return null;
    }

}
