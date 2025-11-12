package ubi.operations;

import ubi.data.transformers.Transformer;

/**
 * Takes numbers and performs operation
 */
@FunctionalInterface
public interface Operation {
    double execute(Transformer input);
}
