package ubi.data.transformers;

public class TransformerFactory {


    /**
     * generates a deep copy of references at its current state
     * not that it works hence its only primitive data types
     *
     * @param register holds state of current map
     * @return deep copy of map
     */
    static ReferenceMap cloneMap(TransformerRegister register) {
        ReferenceMap oldMap = register.refMap;
        ReferenceMap deepCopy = new ReferenceMap();
        for (String key : oldMap.getData().keySet()) {
            deepCopy.put(key, oldMap.get(key));
        }
        return deepCopy;
    }

}
