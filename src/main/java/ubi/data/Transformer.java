package ubi.data;

import java.util.HashMap;
import java.util.Map;

public class Transformer {
    private DataFrame df;
    private final Map<String, Integer> references;

    public Transformer(DataFrame df, Map<RowNameWrapper, Object> functions) {
        this.references = new HashMap<>();
    }


    void eval(LazyRow row) {
    }

    int getTransId(){
        return 0;
    }
}
