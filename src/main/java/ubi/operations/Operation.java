package ubi.operations;

/**
 * Takes numbers and performs operation
 */
@FunctionalInterface
public interface Operation {
    double execute(double input);
}
