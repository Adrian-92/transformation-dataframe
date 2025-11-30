# transformation-model

# Use and purpose

This project implements a simplified dataframe structure that focuses on lazy calculation of column values.

The system models a Directed Acyclic Graph (DAG) of Computations:

* Lazy Evaluation: A column calculation (transformation) is only executed when its result is explicitly requested (e.g., via Dataframe.take()).

* Asynchronous Execution: Dependencies between columns are resolved using Java's CompletableFutures, enabling potential parallel processing of independent dependencies within a single row.

* Per-Row Caching: Each row (LazyRow) maintains its own cache. Once a value for a column has been computed for that row, it is stored and immediately returned on subsequent requests, preventing redundant calculations.

This structure is ideal for defining complex dependency chains in a data pipeline without immediately executing unnecessary intermediate steps.

# Key components

**Dataframe**: The central context and container for all column definitions. Used for initialization and retrieving final results.

**Column**: An auxiliary class that simplifies defining the calculation logic (Lambda) and dependencies for a column.

**Lambda**: A functional interface that defines the custom logic for a column's calculation. It receives a map containing the resolved values of its dependency columns.

# Example usage
````
import ubi.Dataframe;
import ubi.lambda.Lambda;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
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
            return c * 3; // 20.0 * 3 = 60.0
        };
        df.getCol("D").set(dependenciesD, lambdaD);

        // Trigger Calculation and Fetch Results 
        int executionCount = 100;

        // Triggers the lazy evaluation for C.
        List<Object> resultC = df.take("C", executionCount);
        System.out.println("Transformer C got: " + resultC);
        
        // Triggers the lazy evaluation for D, which in turn resolves C, A, and B.
        List<Object> resultD = df.take("D", executionCount);
        System.out.println("Transformer D got: " + resultD);
    }
}
````