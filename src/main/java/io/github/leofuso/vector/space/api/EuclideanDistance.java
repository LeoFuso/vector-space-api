package io.github.leofuso.vector.space.api;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import jdk.incubator.vector.VectorSpecies;

/**
 * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
 */
public sealed interface EuclideanDistance extends DistanceMeasure
        permits EuclideanDistance.NaiveEuclideanDistance, NativeVectorizedEuclideanDistance,
                VectorizedEuclideanDistance {

    static EuclideanDistance withShape(final VectorSpecies<Double> shape) {
        return new VectorizedEuclideanDistance(shape);
    }

    static EuclideanDistance usingNative() {
        return new NativeVectorizedEuclideanDistance();
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

    @Override
    default double compute(MemorySegment x1, MemorySegment x2, int length) {
        double acc = 0;
        for (int i = 0; i < length; i++) {
            final double q = x2.get(ValueLayout.OfDouble.JAVA_DOUBLE, i);
            final double p = x1.get(ValueLayout.OfDouble.JAVA_DOUBLE, i);
            final double diff = q - p;
            acc += diff * diff;
        }
        return Math.sqrt(acc);
    }

    record NaiveEuclideanDistance() implements EuclideanDistance {}
}
