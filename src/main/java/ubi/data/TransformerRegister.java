package ubi.data;

import java.util.ArrayList;
import java.util.List;

/**
 * holds all transformers at time
 * a transformer is the combination of string to id map and id to function map
 */
public final class TransformerRegister {
    static int id;
    private static TransformerRegister instance;
    private static List<Transformer> transformers;

    private TransformerRegister() {
        transformers = new ArrayList<>();
    }

    // it is import that this register is unique
    public synchronized static TransformerRegister getInstance() {
        if (instance == null) {
            instance = new TransformerRegister();
        }
        return instance;

    }

    public static void add(Transformer transformer) {
        transformers.add(transformer);
    }


}
