package ubi;

import java.util.Map;

public class RowNameWrapper {
    Map<String, Integer> references;
    LazyRow row;

    Object get(String name) {
        return row.get(references.get(name)); // TODO: apply magic here
    }



}
