package ubi.evaluation;

import ubi.operations.Operation;

public class LazyObject {
    private boolean isEvaluated;
    private final Operation operation;
    private double result;


    public LazyObject(Operation operation) {
        this.isEvaluated = false;
        this.operation = operation;
    }

    public double evaluate(double a) {
        if (!isEvaluated) {
            this.result = operation.execute(a);
            this.isEvaluated = true;
        }
        return this.result;
    }

}
