package io.github.leofuso.vector.space.api.stress.support;

public interface MemoryFixture {

    long KIB = 1024;
    long MIB = KIB * 1024;

    static long bytesInMiB(int mib) {
        return mib * MIB;
    }

}
