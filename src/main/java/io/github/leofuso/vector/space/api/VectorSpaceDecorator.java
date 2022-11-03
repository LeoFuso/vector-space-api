package io.github.leofuso.vector.space.api;

import java.util.Objects;

public abstract class VectorSpaceDecorator implements VectorSpace {

    protected final double[] pArray;
    protected final double[] qArray;
    private final VectorSpaceDecorator decorator;

    protected VectorSpaceDecorator(final double[] pArray, final double[] qArray, final VectorSpaceDecorator decorator) {
        this.pArray = Objects.requireNonNull(pArray, "pArray is required.");
        this.qArray = Objects.requireNonNull(qArray, "qArray is required.");
        this.decorator = decorator;
    }

}
