package io.github.leofuso.vector.space.api.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.leofuso.vector.space.api.NaiveSquaredEuclideanDistance;
import io.github.leofuso.vector.space.api.SquaredEuclideanDistance;

import static org.junit.jupiter.api.Assertions.*;

class VectorSpaceTest {

    @Test
    @DisplayName("""
            Given
            When
            Then
            """
    )
    void t0() {

        /* Given */
        final int fixedSize = 64;
        double[] a = ArrayFixture.generate(3, fixedSize);
        double[] b = ArrayFixture.generate(4, fixedSize);

        /* When */
        final NaiveSquaredEuclideanDistance naive = new NaiveSquaredEuclideanDistance(a, b);
        final double result = naive.calculate();

        /* Then */
        assertEquals(3.31335, result, 0.00001);

    }

    @Test
    @DisplayName("""
            Given
            When
            Then
            """
    )
    void t1() {

        /* Given */
        final int fixedSize = 64;
        double[] a = ArrayFixture.generate(3, fixedSize);
        double[] b = ArrayFixture.generate(4, fixedSize);

        /* When */
        final NaiveSquaredEuclideanDistance naive = new NaiveSquaredEuclideanDistance(a, b);
        final SquaredEuclideanDistance platform = new SquaredEuclideanDistance(naive);
        final double result = platform.calculate();

        /* Then */
        assertEquals(3.31335, result, 0.00001);

    }

}