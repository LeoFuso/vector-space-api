package io.github.leofuso.vector.space.api.test;

import java.util.Random;

public interface ArrayFixture {

    static double[] generate(int seed, int size) {
        final Random random = new Random(seed);
        final double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }
        return array;
    }

}
