package ubi.data;

public class DataFrame {

    public Column getCol(String name) {
        return new Column(name, this);
    }

}
