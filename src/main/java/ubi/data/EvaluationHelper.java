package ubi.data;

import ubi.evaluation.LazyObject;

import java.util.LinkedList;
import java.util.Optional;


public class EvaluationHelper {
    private LinkedList<LazyObject> objects;

    public EvaluationHelper() {
        objects = new LinkedList<>();
    }

    public Optional<Double> getResult() {
        return Optional.of(0.0);
    }


}
