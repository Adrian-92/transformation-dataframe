package ubi.data;

import ubi.operations.Operation;

public class RowNameWrapper {
    private ReferenceMap refMap;
    private LazyRow row;

    public RowNameWrapper(ReferenceMap refMap, LazyRow row) {
        this.refMap = refMap;
        this.row = row;
    }


    public Operation get(String name) {
        return row.get(refMap.get(name));
    }

}
