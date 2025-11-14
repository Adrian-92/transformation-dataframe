package ubi;


import ubi.data.DataFrame;
import ubi.operations.Operation;

public class Main {

    public static void main(String[] args) {
        DataFrame df = new DataFrame();
        Operation op = (a) -> a * a;
        Operation op1 = (a) -> a + a;

        System.out.println("normal set");
        df.getCol("col").set(op);
        df.getCol("col1").set(op);
        df.getCol("col2").set(op);
        df.getCol("col3").set(op);
        System.out.println("set with override");
        df.getCol("col").set(op1,true);

    }

}