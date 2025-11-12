package ubi.operations;

import ubi.data.Transformer;

/**
 * Takes numbers and performs operation
 */
@FunctionalInterface
public interface Operation {
    // TODO: make this like a callable list
    double execute(double input);
}
