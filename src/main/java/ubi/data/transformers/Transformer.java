package ubi.data.transformers;

import ubi.data.DataFrame;
import ubi.data.LazyRow;
import ubi.data.RowNameWrapper;

import java.util.HashMap;
import java.util.Map;

public class Transformer {
    private DataFrame df;
    private final Map<String, Integer> references;

    public Transformer(DataFrame df, Map<RowNameWrapper, Object> functions) {
        this.df = df;
        this.references = new HashMap<>();
    }


    void eval(LazyRow row) {
    }

    int getTransId(){
        return 0;
    }
}
