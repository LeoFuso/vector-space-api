package io.github.leofuso.vector.space.api;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

import static jdk.incubator.vector.VectorOperators.ADD;

public class VectorizedEuclideanDistance implements EuclideanDistance {

    private static final int POWER = 2;

    private final VectorSpecies<Double> shape;

    public VectorizedEuclideanDistance() {
        this(null);
    }

    public VectorizedEuclideanDistance(final VectorSpecies<Double> shape) {
        this.shape = shape == null ? DoubleVector.SPECIES_PREFERRED : shape;
    }

    @Override
    public double compute(final double[] x1, final double[] x2) {

        DoubleVector accVector = DoubleVector.zero(shape);

        final int length = x1.length;
        final int lowerBound = shape.loopBound(length);

        int pointer = 0;
        for (; pointer < lowerBound; pointer += shape.length()) {
            final DoubleVector pVector = DoubleVector.fromArray(shape, x1, pointer);
            final DoubleVector qVector = DoubleVector.fromArray(shape, x2, pointer);
            final DoubleVector distance = qVector.sub(pVector);
            final DoubleVector norm = distance.pow(POWER);
            accVector = accVector.add(norm);
        }

        double acc = accVector.reduceLanes(ADD);
        if (length != lowerBound) {
            for (; pointer < length; pointer++) {
                final double sub = x2[pointer] - x1[pointer];
                acc += Math.pow(sub, POWER);
            }
        }
        return Math.sqrt(acc);
    }
}
