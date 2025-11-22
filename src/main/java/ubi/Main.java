package ubi;

import ubi.lambda.Lambda;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        Dataframe df = new Dataframe();

        init(df);
        testTake(df);
    }

    public static void init(Dataframe df) {

        // base values
        System.out.println("Col A: 10");
        df.getCol("A").setValue(10.0);
        System.out.println("Col B: 5");
        df.getCol("B").setValue(5.0);

        List<String> dependenciesC = List.of("A", "B");

        Lambda lambdaC = (Map<String, Object> inputs) -> {


            double a = (Double) inputs.get("A");
            double b = (Double) inputs.get("B");

            double result = a + (b * 2);
            System.out.println("Transformer C got: " + result + " with C = A + (B * 2)");
            return result;
        };

        df.getCol("C").set(dependenciesC, lambdaC);


        // D = C * 3
        String colDName = "D";
        List<String> dependenciesD = List.of("C");

        Lambda lambdaD = (Map<String, Object> inputs) -> {

            double c = (Double) inputs.get("C");
            double result = c * 3;
            System.out.println("Transformer D got: " + result + " with D = C * 3");
            return result;
        };

        df.getCol(colDName).set(dependenciesD, lambdaD);

    }


    public static void testA(Dataframe df) {

        // FIXME: wrap this up
        // it is sunday i will take a break and touch grass

        System.out.println("---------------------------------");

        LazyRow currentRow = new LazyRow();
        RowNameWrapper rowWrapper = new RowNameWrapper(df.copyReferences(), currentRow);


        CompletableFuture<Object> resultFutureD = rowWrapper.get("D");

        try {
            Object finalResult = resultFutureD.join();
            System.out.println("---------------------------------");
            System.out.println("Endergebnis für D: " + finalResult);

        } catch (Exception e) {
            System.err.println("Something happened: " + e.getMessage());
        }
    }


    public static void testTake(Dataframe df) {
        System.out.println("------------------------------");
        final int executionCount = 100;
        List<Object> result = df.take("A",executionCount);
        System.out.println("Transformer D got: " + result);
    }
}