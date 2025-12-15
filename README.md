# transformation-model

# Use and purpose

This project implements a simplified dataframe structure that focuses on lazy calculation of column values.

Target of this dataframe is generating synthetic data to get specific self defined drift scenarios without 
depending on pre-generated data.


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

    public static void main(String[] args) {
    
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
}
````