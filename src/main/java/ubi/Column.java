package ubi;

import ubi.lambda.Lambda_One;

public class Column {
    private Dataframe master;
    private String name;

    public Column(String name, Dataframe master) {
        this.name = name;
        this.master = master;
    }

    public void set(Lambda_One function, boolean override) {
        setHelper(function, override);
    }

    private void setHelper(Lambda_One function) {
        setHelper(function, false);
    }

    private void setHelper(Lambda_One function, boolean override) {
        if (!override || !master.hasTransformer(name)) {
            master.setTransformer(name);
        } else {
            RowNameWrapper wrapper = new RowNameWrapper();
            Function f = new Function(); // TODO: apply magic here
            new Transformer(master, f);
            master.getTransformer(name).setLambda(function);
        }
    }
}
