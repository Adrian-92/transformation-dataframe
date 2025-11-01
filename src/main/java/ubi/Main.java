package ubi;


import ubi.operations.Evaluator;
import ubi.operations.Operation;

public class Main {


    public static void main(String[] args) {

        Evaluator evaluator = new Evaluator();

        // test lambda as parameter. works surprisingly well
        double[] someData = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        Operation op = (a) -> a * someData[0] + a * someData[1] + a * someData[2];
        double res = evaluator.performOperation(5, op);

        System.out.println(res);
    }


}