package io.github.leofuso.vector.space.api;

import java.lang.foreign.MemorySegment;

/**
 * Interface for distance measures of n-dimensional vectors.
 */
public interface DistanceMeasure {

    /**
     * Compute the distance between two n-dimensional vectors.
     * <p>
     * The two vectors are required to have the same dimension.
     *
     * @param x1 the first vector
     * @param x2 the second vector
     * @return the distance between the two vectors
     */
    double compute(double[] x1, double[] x2);

    /**
     * Compute the distance between two n-dimensional vectors.
     * <p>
     * The two vectors are required to have the same dimension.
     *
     * @param x1     the first vector
     * @param x2     the second vector
     * @param length the length of the vector
     * @return the distance between the two vectors
     */
    double compute(MemorySegment x1, MemorySegment x2, int length);

}
