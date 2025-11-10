package ubi.data;

import java.util.ArrayList;
import java.util.List;

public class TransformerRegister {
    private List<Transformer> transformers;

    public TransformerRegister() {
        this.transformers = new ArrayList<>();
    }

    public void addTransformer(Transformer transformer) {
        this.transformers.add(transformer);
    }
}
