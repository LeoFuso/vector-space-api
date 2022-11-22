package io.github.leofuso.vector.space.api.test;

import java.io.IOException;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import io.github.leofuso.vector.space.api.EuclideanDistance;
import io.github.leofuso.vector.space.api.DistanceMeasure;
import jdk.incubator.vector.DoubleVector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceMeasureTest {

    private double[] x1;
    private double[] x2;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {

        final Path x1Path = Path.of("src/test/resources/x1-fixed-points");
        final Path x2Path = Path.of("src/test/resources/x2-fixed-points");

        final byte[] x1Bytes = Files.readAllBytes(x1Path);
        final byte[] x2Bytes = Files.readAllBytes(x2Path);

        final DoubleBuffer x1DoubleBuffer = ByteBuffer.wrap(x1Bytes)
                                                      .asDoubleBuffer();

        final DoubleBuffer x2DoubleBuffer = ByteBuffer.wrap(x2Bytes)
                                                      .asDoubleBuffer();

        x1 = new double[x1DoubleBuffer.remaining()];
        x2 = new double[x2DoubleBuffer.remaining()];

        x1DoubleBuffer.get(x1);
        x2DoubleBuffer.get(x2);
    }

    @RepeatedTest(10)
    @DisplayName("""
            Given NaiveSquaredEuclideanDistance run,
            then calculate() invocation should return value within margin of error.
            """
    )
    void t0() throws IOException {

        /* Given */
        final DistanceMeasure measure = new EuclideanDistance.NaiveEuclideanDistance();

        /* When */
        final double result = measure.compute(x1, x2);

        /* Then */
        assertEquals(3.90926, result, 0.00001);
    }

    @RepeatedTest(10)
    @DisplayName("""
            Given VectorizedSquaredEuclideanDistance run,
            then calculate() invocation should return value within margin of error.
            """
    )
    void t1() {

        /* Given */
        final DistanceMeasure measure = EuclideanDistance.withShape(DoubleVector.SPECIES_PREFERRED);

        /* When */
        final double result = measure.compute(x1, x2);

        /* Then */
        assertEquals(3.90926, result, 0.00001);
    }

    @RepeatedTest(10)
    @DisplayName("""
            Given NativeVectorizedSquaredEuclideanDistance run,
            then calculate() invocation should return value within margin of error.
            """
    )
    void t2() {

        try(final MemorySession session = MemorySession.openShared()) {

            /* Given */
            final int oneMb = 1024 * 1024;
            final MemorySegment s1 = MemorySegment.allocateNative(
                    oneMb,
                    ValueLayout.OfDouble.JAVA_DOUBLE.byteAlignment(),
                    session
            );

            final MemorySegment s2 = MemorySegment.allocateNative(
                    oneMb,
                    ValueLayout.OfDouble.JAVA_DOUBLE.byteAlignment(),
                    session
            );

            for (int i = 0; i < x1.length; i++) {
                s1.setAtIndex(ValueLayout.OfDouble.JAVA_DOUBLE, i, x1[i]);
                s2.setAtIndex(ValueLayout.OfDouble.JAVA_DOUBLE, i, x2[i]);
            }

            final DistanceMeasure measure = EuclideanDistance.usingNative();

            /* When */
            final double result = measure.compute(s1, s2, x1.length);

            /* Then */
            assertEquals(3.90926, result, 0.00001);
        }
    }
}