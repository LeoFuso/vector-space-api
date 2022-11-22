package io.github.leofuso.vector.space.api;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;

import static io.github.leofuso.vector.space.api.foreign.distance__m256d_h.euclidean;

public final class NativeVectorizedEuclideanDistance implements EuclideanDistance {

    @Override
    public double compute(final MemorySegment x1, final MemorySegment x2, final int length) {
        final MemoryAddress pAddress = x1.address();
        final MemoryAddress qAddress = x2.address();
        return euclidean(pAddress, qAddress, length);
    }
}
