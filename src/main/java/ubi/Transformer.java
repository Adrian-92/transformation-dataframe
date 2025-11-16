package ubi;

import ubi.lambda.Lambda_One;

import java.util.Map;

public class Transformer {

    Function function;
    private final int id;
    private final Map<String, Integer> references;

    public Transformer(Dataframe df, Function f) {
        this.references = df.copyReferences();
        this.function = f;
        this.id = TransformerRegister.nextTransformerId();
        TransformerRegister.add(this);
    }

    void eval(LazyRow row) {
        if (row.has(getTransId())) {
            row.set(getTransId(), function.execute(new RowNameWrapper(references, row)));
        }
    }


    public int getTransId() {
        return this.id;
    }

    void setLambda(Lambda_One lambda) {
        // TODO: apply magic here
    }
}
