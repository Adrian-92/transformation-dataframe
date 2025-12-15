package ubi;

import ubi.lambda.Lambda;
import ubi.transformer.Dataframe;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nMinimal scenario");
        testMinimal();
        System.out.println("\nScenario with row indices");
        testIndexScenario();
        System.out.println("\nScenario with dependencies");
        testDependencyScenario();
    }


    /**
     * this is the standard usage scenario
     * define a column and set it to any function or value
     * if you set the override flag and set the same column to another function or value
     * you set a new function
     * <p>
     * by using the take-function you take n rows of this column
     */
    private static void testMinimal() {
        Dataframe df = new Dataframe();
        System.out.println("\n--- Some data ---");
        df.getCol("A").set(inputs -> 20.0 + Math.random()); // normal

        var batch1 = df.take(10);
        System.out.println(batch1);

        df.getCol("A").set(inputs -> 50.0 + Math.random(), true); // drift

        System.out.println("\n--- Some drift ---");
        var batch2 = df.take(10);
        System.out.println(batch2);
    }

    /**
     * this scenario shows a usage where you can set the rows where drift occurs
     * by addressing the row itself with
     * int row = (int) inputs.get("_ROW_");
     * note that "_ROW_" is therefore a reserved key for the column name and cannot be used
     */
    private static void testIndexScenario() {
        Dataframe df = new Dataframe();

        // some linear rising value based on row index
        Lambda trendLogic = (inputs) -> {
            int row = (int) inputs.get("_ROW_");
            return 20.0 + (row * 0.1);
        };

        df.getCol("Sensor").set(trendLogic);

        System.out.println("---normal scenario ---");
        var batch1 = df.take(10);
        System.out.println(batch1);


        // (Override = true) some artificial defect
        Lambda defectLogic = (inputs) -> {
            int row = (int) inputs.get("_ROW_");
            return 100.0 + (Math.sin(row) * 5.0);
        };

        df.getCol("Sensor").set(defectLogic, true);

        System.out.println("\n--- drift scenario ---");
        var batch2 = df.take(10);
        System.out.println(batch2);


        // artificial defect repaired
        df.getCol("Sensor").set(trendLogic, true);

        System.out.println("\n--- normal scenario again ---");
        var batch3 = df.take(10);
        System.out.println(batch3);
    }

    private static void testDependencyScenario() {
        Dataframe df = new Dataframe();

        df.getCol("Price").set(10.0);

        df.getCol("Sales").set(inputs -> {
            int row = (int) inputs.get("_ROW_");
            return 100.0 + (double) row;
        });


        df.getCol("Revenue").set(inputs -> {
            double p = (Double) inputs.get("Price");
            double s = (Double) inputs.get("Sales");
            return p * s;
        });

        System.out.println("--- price at 10 ---");
        System.out.println(df.take(5));


        df.getCol("Price").set(20.0, true); // override=true

        System.out.println("\n--- price rises to 20.0 ---");
        System.out.println(df.take(5));

        df.getCol("Sales").set(inputs -> 50.0 + Math.random(), true);

        System.out.println("\n--- sales crash ---");
        System.out.println(df.take(5));

    }
}