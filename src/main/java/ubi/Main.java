package ubi;

import ubi.lambda.Lambda;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        Dataframe df = new Dataframe();

        // Define Source Columns
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);

        //  Define Derived Columns
        List<String> dependenciesC = List.of("A", "B");
        Lambda lambdaC = (Map<String, Object> inputs) -> {
            double a = (Double) inputs.get("A");
            double b = (Double) inputs.get("B");
            return a + (b * Math.random());  // for instance some random function
        };
        df.getCol("C").set(dependenciesC, lambdaC);

        // Define further Derived Columns
        List<String> dependenciesD = List.of("C");
        Lambda lambdaD = (Map<String, Object> inputs) -> {
            double c = (Double) inputs.get("C");
            return c * 3;
        };
        df.getCol("D").set(dependenciesD, lambdaD);

        // Trigger Calculation and Fetch Results
        int executionCount = 10;

        // Triggers the lazy evaluation for C.
        List<Object> resultC = df.take("C", executionCount);
        System.out.println("Transformer C got: " + resultC);

        // Triggers the lazy evaluation for D, which in turn resolves C, A, and B.
        List<Object> resultD = df.take("D", executionCount);
        System.out.println("Transformer D got: " + resultD);

        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);
    }

    private static void test2() {
        Dataframe df = new Dataframe();

        // Define Source Columns
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);

        //  Define Derived Columns
        List<String> dependenciesC = List.of("A", "B");
        // "real" lambda
        Lambda lambdaC = (Map<String, Object> inputs) -> (Double) inputs.get("A") + ((Double) inputs.get("B") * 2);
        df.getCol("C").set(dependenciesC, lambdaC);
        Lambda lambdaA = (Map<String, Object> inputs) -> (Double) 666.0;

        System.out.println("Transformer C got: " + df.take("C", 5));
        // test override should not change the values
        System.out.println("Transformer C with override  false");
        df.getCol("A").set(lambdaA);
        System.out.println(df.take("C", 5));

        System.out.println("Transfomrer C with override  true");
        // this should change the result
        df.getCol("A").set(lambdaA, true);

        System.out.println(df.take("C", 5));


    }
}