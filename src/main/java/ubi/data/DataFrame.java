package ubi.data;

import ubi.evaluation.LazyObject;
import ubi.operations.Operation;

import java.util.ArrayList;
import java.util.List;


public class DataFrame {

    private final List<LazyObject> data;
    private final DataFactory dataFactory;


    public DataFrame() {
        this.data = new ArrayList<>();
        this.dataFactory = new DataFactory();
    }

    public void apply(String name, Operation operation) {
        dataFactory.apply(name, operation);
        int id = getId(name);
        LazyObject obj = new LazyObject(id, operation);
        data.add(obj);
    }

    public void take(int n) {
        dataFactory.take(n);
    }

    public int getId(String name) {
        return dataFactory.getStringMap().get(name);
    }

    public LazyObject getLazyObject(String name) {
        int id = getId(name);
        return data.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "data=" + data +
                '}';
    }
}
