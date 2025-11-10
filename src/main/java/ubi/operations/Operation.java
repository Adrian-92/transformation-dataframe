package ubi.operations;

import ubi.data.RowNameWrapper;

/**
 * Takes numbers and performs operation
 */
@FunctionalInterface
public interface Operation {
    double execute(RowNameWrapper input);
}
