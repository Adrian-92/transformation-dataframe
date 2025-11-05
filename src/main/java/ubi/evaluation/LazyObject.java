package ubi.evaluation;

import ubi.operations.Operation;

import java.util.Objects;

public class LazyObject {
    private final int id;
    private boolean isEvaluated;
    private Operation operation;
    private String operationString;
    private double result;


    public LazyObject(int id, String operationString, Operation operation) {
        this.id = id;
        this.isEvaluated = false;
        this.operation = operation;
        this.operationString = operationString;
    }

    public double evaluate(double a) {
        if (!isEvaluated) {
            this.result = operation.execute(a);
            this.isEvaluated = true;
        }
        return this.result;
    }

    public void setOperationString(String operationString) {
        this.operationString = operationString;
    }

    public String getOperationString() {
        return operationString;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LazyObject that = (LazyObject) o;
        return id == that.id && isEvaluated == that.isEvaluated && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isEvaluated, operation);
    }

    @Override
    public String toString() {
        return "{id: " + id +
                " operation: " + operationString +
                '}';
    }
}
