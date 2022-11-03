package io.github.leofuso.vector.space.api.test;

public interface ArraySizeFixture {

    static int cacheL3() {
        // TODO Perform a System call 'getconf -a | grep CACHE'
        return 64;
    }

}
