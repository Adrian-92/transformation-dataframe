package ubi;

import ubi.lambda.Lambda;

import java.util.List;

public class Column {
    private final Dataframe master;
    private final String name;
    private List<String> dependencies;

    public Column(String name, Dataframe master) {
        this.name = name;
        this.master = master;
        this.dependencies = List.of();
    }

    /**
     * Sets constant value for column (Source Column).
     * Registers a transformer with no dependencies.
     * Use this one as start point for a new column.
     *
     * @param value Constant value for column (for example Double, String)
     */
    public void set(Object value) {
        // Creates a Lambda that just returns the constant value
        Lambda constantLambda = inputs -> value;
        // Source column has no dependencies - see constructor
        setHelper(constantLambda, false);
    }


    /**
     * Defines calculation logic for a column.
     * If override flag is set an existing transformer will be overwritten.
     *
     * @param function The {@link Lambda}-Function for calculating column values
     * @param override if true an existing transformer will be overwritten
     */
    public void set(Lambda function, boolean override) {
        setHelper(function, override);
    }

    /**
     * Defines calculation logic for a column without changing its dependencies.
     * An existing transformer will NOT be overwritten.
     *
     * @param function The {@link Lambda}-Function for calculating column values
     */
    public void set(Lambda function) {
        setHelper(function, false);
    }


    /**
     * Sets dependencies for evaluation and calculation logic for a column.
     * This is the primary way to define a derived column dependent on other columns.
     * If override flag is set an existing transformer will be overwritten.
     *
     * @param dependencies a list of column names which are needed as input for its {@code function}
     * @param function     The {@link Lambda}-Function for calculating column values
     * @param override     if true an existing transformer will be overwritten
     */
    public void set(List<String> dependencies, Lambda function, boolean override) {
        setDependencies(dependencies);
        setHelper(function, override);
    }

    /**
     * Sets dependencies for evaluation and calculation logic for a column
     * This is the primary way to define a derived column dependent on other columns
     * an existing transformer will NOT be overwritten
     *
     * @param dependencies a list of column names which are needed as input for its {@code function}
     * @param function     The {@link Lambda}-Function for calculating column values
     */
    public void set(List<String> dependencies, Lambda function) {
        setDependencies(dependencies);
        setHelper(function, false);
    }

    /* ################ Internal helpers ################ */

    private void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    private void setHelper(Lambda function, boolean override) {
        boolean exists = master.hasTransformer(name);

        if (exists && !override) {
            // hier passiert später etwas anderes
            return;
        }

        Function f = new Function(dependencies);
        f.setLambda(function);
        Transformer t;
        if (exists) {
            Integer oldTransId = master.getTransformerId(name);
            if (oldTransId != null) {
                TransformerRegister.remove(oldTransId);
                t = new Transformer(master, f, oldTransId);
                master.addTransformer(name, t.getTransId());

            }
        } else {
            t = new Transformer(master, f);
            master.addTransformer(name, t.getTransId());
        }

    }
}