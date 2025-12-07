package ubi;

import ubi.lambda.Lambda;
import ubi.transformer.Dataframe;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
    }

    private static void test1() {
        Dataframe df = new Dataframe();
        // Define Source Columns
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);
        //  Define Derived Columns
        Lambda lambdaC = (Map<String, Object> inputs) -> {
            double a = (Double) inputs.get("A");
            double b = (Double) inputs.get("B");
            return a + (b * Math.random());  // for instance some random function
        };
        df.getCol("C").set(lambdaC);
        // Define further Derived Columns
        Lambda lambdaD = (Map<String, Object> inputs) -> {
            double c = (Double) inputs.get("C");
            return c * 3;
        };
        df.getCol("D").set(lambdaD);
        // Trigger Calculation and Fetch Results
        int executionCount = 10;
        System.out.println(df.take(executionCount));
    }

    private static void test2() {
        Dataframe df = new Dataframe();

        // Define Source Columns
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);

        // "real" lambda
        Lambda lambdaC = (Map<String, Object> inputs) -> (Double) inputs.get("A") + ((Double) inputs.get("B") * 2);
        df.getCol("C").set(lambdaC);

        System.out.println("Before override");
        System.out.println(df.take(3));
        Lambda lambdaA = (Map<String, Object> inputs) -> (Double) 666.0;

        System.out.println("A with override = true");
        // this should change the result
        df.getCol("A").set(lambdaA, true);
        System.out.println(df.take(3));
    }

    private static void test3() {

        Dataframe df = new Dataframe();

        // Define Source Columns
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);

        //  Define Derived Columns
        df.getCol("C").set((Map<String, Object> inputs) -> (Double) inputs.get("A") + ((Double) inputs.get("B") * Math.random()));
        System.out.println(df.take(5));
    }
}