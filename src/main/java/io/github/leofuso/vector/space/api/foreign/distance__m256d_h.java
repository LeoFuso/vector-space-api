// Generated by jextract

package io.github.leofuso.vector.space.api.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class distance__m256d_h  {

    /* package-private */ distance__m256d_h() {}
    public static OfByte C_CHAR = Constants$root.C_CHAR$LAYOUT;
    public static OfShort C_SHORT = Constants$root.C_SHORT$LAYOUT;
    public static OfInt C_INT = Constants$root.C_INT$LAYOUT;
    public static OfLong C_LONG = Constants$root.C_LONG_LONG$LAYOUT;
    public static OfLong C_LONG_LONG = Constants$root.C_LONG_LONG$LAYOUT;
    public static OfFloat C_FLOAT = Constants$root.C_FLOAT$LAYOUT;
    public static OfDouble C_DOUBLE = Constants$root.C_DOUBLE$LAYOUT;
    public static OfAddress C_POINTER = Constants$root.C_POINTER$LAYOUT;
    public static MethodHandle aligned_alloc$MH() {
        return RuntimeHelper.requireNonNull(constants$0.aligned_alloc$MH,"aligned_alloc");
    }
    public static MemoryAddress aligned_alloc ( long __alignment,  long __size) {
        var mh$ = aligned_alloc$MH();
        try {
            return (java.lang.foreign.MemoryAddress)mh$.invokeExact(__alignment, __size);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle cosine$MH() {
        return RuntimeHelper.requireNonNull(constants$0.cosine$MH,"cosine");
    }
    public static double cosine ( Addressable x0,  Addressable x1,  long x2) {
        var mh$ = cosine$MH();
        try {
            return (double)mh$.invokeExact(x0, x1, x2);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle euclidean$MH() {
        return RuntimeHelper.requireNonNull(constants$0.euclidean$MH,"euclidean");
    }
    public static double euclidean ( Addressable x0,  Addressable x1,  long x2) {
        var mh$ = euclidean$MH();
        try {
            return (double)mh$.invokeExact(x0, x1, x2);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle manhattan$MH() {
        return RuntimeHelper.requireNonNull(constants$0.manhattan$MH,"manhattan");
    }
    public static double manhattan ( Addressable x0,  Addressable x1,  long x2) {
        var mh$ = manhattan$MH();
        try {
            return (double)mh$.invokeExact(x0, x1, x2);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}


