# transformation-model

# Use and purpose

This project implements a simplified dataframe structure that focuses on lazy calculation of column values.

The system models a Directed Acyclic Graph (DAG) of Computations:

* Lazy Evaluation: A column calculation (transformation) is only executed when its result is explicitly requested (e.g.,
  via Dataframe.take()).

* Asynchronous Execution: Dependencies between columns are resolved using Java's CompletableFutures, enabling potential
  parallel processing of independent dependencies within a single row.

* Per-Row Caching: Each row (LazyRow) maintains its own cache. Once a value for a column has been computed for that row,
  it is stored and immediately returned on subsequent requests, preventing redundant calculations.

This structure is ideal for defining complex dependency chains in a data pipeline without immediately executing
unnecessary intermediate steps.

# Key components

**Dataframe**: The central context and container for all column definitions. Used for initialization and retrieving
final results.

**Column**: An auxiliary class that simplifies defining the calculation logic (Lambda) and dependencies for a column.

**Lambda**: A functional interface that defines the custom logic for a column's calculation. It receives a map
containing the resolved values of its dependency columns.

# Example usage
see Main-Class for further examples

````
import ubi.transformer.Dataframe;
import ubi.lambda.Lambda;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {Dataframe df = new Dataframe();
        System.out.println("\n--- Some data ---");
        df.getCol("A").set(inputs -> 20.0 + Math.random()); // normal

        var batch1 = df.take(10);
        System.out.println(batch1);

        df.getCol("A").set(inputs -> 50.0 + Math.random(), true); // drift

        System.out.println("\n--- Some drift ---");
        var batch2 = df.take(10);
        System.out.println(batch2);
    }
}
````