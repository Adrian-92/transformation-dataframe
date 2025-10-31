package ubi;

/**
 * Takes numbers and performs operation
 */
@FunctionalInterface
public interface Operation {
    double execute(double input);
}
