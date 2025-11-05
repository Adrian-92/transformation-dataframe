package ubi;


import ubi.data.DataFrame;
import ubi.evaluation.LazyObject;

import ubi.operations.Operation;

public class Main {


    public static void main(String[] args) {


        // test lambda as parameter. works surprisingly well
        double[] someData = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        Operation op1 = (a) -> a * someData[0] + a * someData[1];
        // output in toString currently is something like this: "ubi.Main$$Lambda/0x00007f5c68003720@7530d0a"
        // this is a workaround until I have a better solution
        String opString1 = "(a) -> a * someData[0] + a * someData[1]";
        Operation op2 = (a) -> a + someData[2] + a + someData[3];
        String opString2 = "(a) -> a * someData[2] + a * someData[3]";
        DataFrame df = new DataFrame();

        df.apply("first op", opString1, op1);
        df.apply("second op", opString2, op2);

        int id1 = df.getId("first op");
        int id2 = df.getId("second op");

        System.out.println("id1: " + id1);
        System.out.println("id2: " + id2);

        LazyObject lObj1 = df.getLazyObject("first op");
        LazyObject lObj2 = df.getLazyObject("second op");

        System.out.println("lObj1: " + lObj1);
        System.out.println("lObj2: " + lObj2);
        double result1 = lObj1.evaluate(5);
        double result2 = lObj2.evaluate(5);

        System.out.println(df);


        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);

    }


}