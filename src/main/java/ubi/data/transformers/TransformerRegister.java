package ubi.data.transformers;

/**
 * holds all transformers at time
 * a transformer is the combination of string to id map and id to function map
 */
public final class TransformerRegister {
    // the one and only holy id of operations
    static int id;
    private final OperationMap operationMap;
    final ReferenceMap refMap;

    private static TransformerRegister instance;

    private TransformerRegister() {
        operationMap = new OperationMap();
        refMap = new ReferenceMap();
    }

    // it is import that this register is unique
    public synchronized static TransformerRegister getInstance() {
        if (instance == null) {
            instance = new TransformerRegister();
        }
        return instance;

    }

    public void add() {

    }

    public Transformer getTransformer() {
        return null;
    }

    public OperationMap getOperationMap() {
        return operationMap;
    }

    public ReferenceMap getRefMap() {
        return refMap;
    }
}
