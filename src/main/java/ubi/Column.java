package ubi;

import ubi.lambda.Lambda;

import java.util.List;

public class Column {
    private final Dataframe master;
    private final String name;

    public Column(String name, Dataframe master) {
        this.name = name;
        this.master = master;
    }

    /**
     * Sets constant value for column (Source Column)
     * Registers a transformer with no dependencies
     * use this one as start point for a new column
     * !!!here will be a better description later!!!
     *
     * @param value Constant value for column (for example Double, String)
     */
    void setValue(Object value) {

        // Creates a Lambda that just returns the constant value
        Lambda constantLambda = inputs -> value;

        // Source column has no dependencies
        List<String> dependencies = List.of();
        setHelper(dependencies, constantLambda, false);
    }

    /**
     * sets parameters for evaluation
     * if you have a start point defined use its name as an entry in dependencies
     * define a function as you like
     * <p>
     * !!!here will be a better description later!!!
     *
     * @param dependencies defines which columns are needed
     * @param function     The Lambda function to execute
     * @param override     names says it
     */
    void set(List<String> dependencies, Lambda function, boolean override) {
        setHelper(dependencies, function, override);
    }

    /**
     * same as above
     * !!!here will be a better description later!!!
     *
     * @param dependencies defines which columns are needed
     * @param function     The Lambda function to execute
     */
    void set(List<String> dependencies, Lambda function) {
        setHelper(dependencies, function, false);
    }

    private void setHelper(List<String> dependencies, Lambda function, boolean override) {
        if (master.hasTransformer(name) && !override) {
            return;
        }

        Function f = new Function(dependencies);
        f.setLambda(function);

        Transformer t = new Transformer(master, f);
        master.addTransformer(name, t.getTransId());

    }
}