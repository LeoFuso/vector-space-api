package io.github.leofuso.vector.space.api;

import jdk.incubator.vector.VectorSpecies;

/**
 * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
 *
 */
public interface EuclideanDistance extends DistanceMeasure {

    static EuclideanDistance withShape(final VectorSpecies<Double> shape) {
        return new VectorizedEuclideanDistance(shape);
    }

    static EuclideanDistance naive() {
        return new NaiveEuclideanDistance();
    }

    @Override
    default double compute(double[] x1, double[] x2) {
        double acc = 0;
        for (int i = 0; i < x1.length; i++) {
            final double diff = x2[i] - x1[i];
            acc += diff * diff;
        }
        return Math.sqrt(acc);
    }


    record NaiveEuclideanDistance() implements EuclideanDistance {}
}
