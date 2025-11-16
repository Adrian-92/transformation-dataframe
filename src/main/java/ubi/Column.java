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
        if (!override || !master.hasTransformer(name)) { // shouldn't this be the other way around?
            master.setTransformer(name, function);
        } else {
            RowNameWrapper wrapper = new RowNameWrapper();

            /*
            where and how and WHY to get/generate this?
            // Map<String,Int> refs , LazyRow Map<Integer, Objects>
            */

            Function f = new Function(); // TODO: apply magic here
            Transformer t = new Transformer(master, f);
            t.setLambda(function);
        }
    }
}
