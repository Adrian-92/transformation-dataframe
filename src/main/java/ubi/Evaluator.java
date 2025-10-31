package ubi;


public class Evaluator {
    public double performOperation(double input, Operation op) {
        return op.execute(input);
    }
}
