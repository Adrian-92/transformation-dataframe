package ubi;

import ubi.lambda.Lambda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

class Function {

    private Lambda lambda;
    private final List<String> requiredInputs;

    Function(List<String> requiredInputs) {
        this.requiredInputs = requiredInputs;
    }

    void setLambda(Lambda lambda) {
        this.lambda = lambda;
    }


    CompletableFuture<Object> execute(RowNameWrapper rowNameWrapper) {
        if (requiredInputs.isEmpty()) {
            // fallback if there are no dependencies set in column
            return CompletableFuture.completedFuture(null);
        }
        // Collect all necessary dependency CompletableFutures
        List<CompletableFuture<Object>> dependencyFutures = requiredInputs.stream()
                // Each get call starts a recursive/lazy evaluation chain
                .map(rowNameWrapper::get)
                .toList();

        // Create a Future that waits for ALL dependent Futures to complete
        CompletableFuture<Void> allDependencies = CompletableFuture.allOf(
                dependencyFutures.toArray(new CompletableFuture[0])
        );

        // Chain the final execution, runs only after all dependencies are resolved
        return allDependencies.thenApplyAsync(v -> {

            Map<String, Object> inputs = new HashMap<>();

            // Extract results from the completed Futures
            for (int i = 0; i < requiredInputs.size(); i++) {
                String name = requiredInputs.get(i);
                CompletableFuture<Object> future = dependencyFutures.get(i);

                inputs.put(name, future.join());
            }
            return lambda.execute(inputs);
        });
    }
}
