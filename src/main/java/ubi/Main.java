package ubi;

import ubi.lambda.Lambda;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Dataframe df = new Dataframe();

        init(df);
        testTake(df);

    }

    public static void init(Dataframe df) {

        // base values
        df.getCol("A").set(10.0);
        df.getCol("B").set(5.0);


        List<String> dependenciesC = List.of("A", "B");

        Lambda lambdaC = (Map<String, Object> inputs) -> {

            double a = (Double) inputs.get("A");
            double b = (Double) inputs.get("B");

            return a + (b * 2);
        };
        df.getCol("C").set(dependenciesC, lambdaC);


        // D = C * 3
        String colDName = "D";
        List<String> dependenciesD = List.of("C");

        Lambda lambdaD = (Map<String, Object> inputs) -> {
            double c = (Double) inputs.get("C");
            return c * 3;
        };

        df.getCol(colDName).set(dependenciesD, lambdaD);

    }



    public static void testTake(Dataframe df) {
        int executionCount = 100;
        List<Object> result = df.take("D",executionCount);
        System.out.println("Transformer D got: " + result);
        List<Object> result2 = df.take("C",executionCount);
        System.out.println("Transformer C got: " + result2);
    }
}