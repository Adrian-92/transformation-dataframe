package ubi;


import ubi.data.DataFrame;
import ubi.evaluation.LazyObject;

import ubi.operations.Operation;

public class Main {


    public static void main(String[] args) {


        // test lambda as parameter. works surprisingly well
        double[] someData = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        Operation op1 = (a) -> a * someData[0] + a * someData[1] ;
        Operation op2 = (a) -> a + someData[2] + a + someData[3] ;
        DataFrame df = new DataFrame();

        df.apply("first op", op1);
        df.apply("second op", op2);

        int id1 = df.getId("first op");
        int id2 = df.getId("second op");

        System.out.println("id1: " + id1);
        System.out.println("id2: " + id2);

        LazyObject lObj1 = df.getLazyObject("first op");
        LazyObject lObj2 = df.getLazyObject("second op");

        System.out.println("lObj1: " + lObj1);
        System.out.println("lObj2: " + lObj2);

        System.out.println(df);

    }


}