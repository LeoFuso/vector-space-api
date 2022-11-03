package io.github.leofuso.vector.space.api;

public class NaiveSquaredEuclideanDistance extends VectorSpaceDecorator {

    public NaiveSquaredEuclideanDistance(final double[] p, final double[] q) {
        super( p, q, null);
    }

    @Override
    public double calculate() {
        double acc = 0;
        for (int i = 0; i < pArray.length; i++) {
            final double diff = qArray[i] - pArray[i];
            acc += diff * diff;
        }
        return Math.sqrt(acc);
    }
}
