package io.github.leofuso.vector.space.api.stress.support;

import java.util.Random;

public interface ArrayFixture {

    static double[] generate(int seed, long size) {
        final Random random = new Random(seed);
        final double[] array = new double[(int) size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }
        return array;
    }

}
