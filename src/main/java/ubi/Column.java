package ubi;

import ubi.lambda.Lambda_One;

public class Column {
    Dataframe master;
    String name;

    public Column(String name, Dataframe master) {
        this.name = name;
        this.master = master;
    }

    public void set(Lambda_One function, boolean override) {
        setHelper(function, override);
    }

    public void setHelper(Lambda_One function) {
        setHelper(function, false);
    }

    private void setHelper(Lambda_One function, boolean override) {
        if (!override || !master.hasTransformer(name)) {
            master.setTransformer(name);
        } else {
            Function f = new Function(); // TODO: apply magic here
            new Transformer(master, f);
            master.getTransformer(name).setLambda(function);
        }
    }
}
