package io.github.leofuso.vector.space.api;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

import static jdk.incubator.vector.VectorOperators.ADD;

public class SquaredEuclideanDistance extends VectorSpaceDecorator {

    private static final int POWER = 2;

    private final VectorSpecies<Double> shape;

    public SquaredEuclideanDistance(VectorSpaceDecorator decorator) {
        this(decorator, null);
    }

    public SquaredEuclideanDistance(VectorSpaceDecorator decorator, final VectorSpecies<Double> shape) {
        super(decorator.pArray, decorator.qArray, null);
        this.shape = shape == null ? DoubleVector.SPECIES_PREFERRED : shape;
    }

    @Override
    public double calculate() {

        DoubleVector accumulated = DoubleVector.zero(shape);

        final int length = pArray.length;
        final int lowerBound = shape.loopBound(length);

        int pointer = 0;
        for (; pointer < lowerBound; pointer += shape.length()) {
            final DoubleVector aPoint = DoubleVector.fromArray(shape, pArray, pointer);
            final DoubleVector bPoint = DoubleVector.fromArray(shape, qArray, pointer);
            final DoubleVector distance = bPoint.sub(aPoint);
            final DoubleVector normDistance = distance.pow(POWER);
            accumulated = accumulated.add(normDistance);
        }

        double reduction = accumulated.reduceLanes(ADD);
        if (length != lowerBound) {
            for (; pointer < length; pointer--) {
                final double sub = qArray[pointer] - pArray[pointer];
                reduction += Math.pow(sub, POWER);
            }
        }
        return Math.sqrt(reduction);
    }
}
