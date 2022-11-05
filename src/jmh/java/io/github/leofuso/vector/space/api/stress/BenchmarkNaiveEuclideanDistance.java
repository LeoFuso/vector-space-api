package io.github.leofuso.vector.space.api.stress;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import io.github.leofuso.vector.space.api.EuclideanDistance;
import io.github.leofuso.vector.space.api.DistanceMeasure;
import io.github.leofuso.vector.space.api.stress.support.ArrayFixture;
import io.github.leofuso.vector.space.api.stress.support.MemoryFixture;


@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class BenchmarkNaiveEuclideanDistance {

    private DistanceMeasure subject;

    private double[] x1;
    private double[] x2;

    @Setup
    public void setup() {
        final long fixedSize = MemoryFixture.bytesInMiB(64);
        x1 = ArrayFixture.generate(7, fixedSize);
        x2 = ArrayFixture.generate(23, fixedSize);
        subject = EuclideanDistance.naive();
    }

    @Benchmark
    public void b1() {
        subject.compute(x1, x2);
    }

}
