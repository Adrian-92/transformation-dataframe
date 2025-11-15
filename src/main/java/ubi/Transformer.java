package ubi;

import ubi.lambda.Lambda_One;

import java.util.Map;

public class Transformer {
    Dataframe df;
    Function function;
    private final Map<String, Integer> references;

    public Transformer(Dataframe df, Function f) {
        this.references = df.copyReferences(); // TODO: deep copy here
        this.df = df;
        this.function = f;
    }

    void eval(LazyRow row) {
        if (row.has(getTransId())) {

        }
    }


    int getTransId() {
        return 0;
    }

    void setLambda(Lambda_One lambda) {
        // TODO: apply magic here
    }
}
