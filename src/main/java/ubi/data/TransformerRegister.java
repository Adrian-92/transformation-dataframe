package ubi.data;


import ubi.operations.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * holds all transformers at time
 * a transformer is the combination of string to id map and id to function map
 */
public class TransformerRegister {
    static int id;
    private static final OperationMap operationMap = OperationMap.getInstance();
    private static final ReferenceMap refMap = ReferenceMap.getInstance();
    private static final List<Transformer> transformers = new ArrayList<>();


    public static void add(Transformer transformer) {
        refMap.put(transformer.getName()); // id is generated in refMap
        operationMap.put(id, transformer.getF());
        transformers.add(transformer);
        System.out.println(" added transformer id " + id + " with name: " + transformer.getName() + " to register");
    }

    public static void rename(Transformer transformer) {
        int id = transformer.getTransId(transformer.getName());


    }

    // do I need this?
    public static Transformer getTransformer(String name) {
        for (Transformer transformer : transformers) {
            if (transformer.getName().equals(name)) {
                return transformer;
            }
        }
        return null;
    }

}
