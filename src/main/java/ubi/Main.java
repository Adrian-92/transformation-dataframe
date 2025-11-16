package ubi;

import ubi.lambda.Lambda;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        Dataframe df = new Dataframe();

        init(df);
        testB(df);
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

    public static void testB(Dataframe df) {

        System.out.println("---------------------------------");

        // FIXME: this will be the take method
        // for now its just a test

        final int executionCount = 1000;
        // just to take a look at the time
        long startTime = System.currentTimeMillis();


        for (int i = 1; i <= executionCount; i++) {
            System.out.printf("\nRow %d / %d started\n", i, executionCount);

            // each row must instantiate a new lazy row
            // this is kind of caching
            LazyRow currentRow = new LazyRow();

            RowNameWrapper rowWrapper = new RowNameWrapper(df.copyReferences(), currentRow);

            CompletableFuture<Object> resultFuture = rowWrapper.get("D");

            try {
                Object result = resultFuture.join();
                System.out.printf("Row %d finished, result: %.1f\n", i, (Double) result);
            } catch (Exception e) {
                System.err.println("Error in row " + i + ": " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n---------------------------------");
        System.out.printf("All %d rows done\n", executionCount);
        System.out.printf("time: %d ms\n", duration);
        System.out.println("---------------------------------");
    }
}