package ubi.data.transformers;

import java.util.ArrayList;
import java.util.List;

/**
 * holds all transformers at time
 */
public final class TransformerRegister {
    private final List<Transformer> transformers;
    private static TransformerRegister instance;

    private TransformerRegister() {
        this.transformers = new ArrayList<>();
    }

    public synchronized static TransformerRegister getInstance() {
        if (instance == null) {
            instance = new TransformerRegister();
        }
        return instance;

    }

    public void addTransformer(Transformer transformer) {
        this.transformers.add(transformer);
    }

    public Transformer getTransformer(String name) {
        return null;
    }
}
