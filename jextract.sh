#!/bin/bash

cd src/main/java || exit
~/.sdkman/candidates/jextract-19/bin/jextract \
                  --source \
                  --target-package io.github.leofuso.vector.space.api.foreign \
                  -ldistance__m256d \
                  -I /usr/local/include /usr/local/include/distance__m256d.h \
                  --include-function euclidean \
                  --include-function manhattan \
                  --include-function cosine \
                  --include-function aligned_alloc
cd ..