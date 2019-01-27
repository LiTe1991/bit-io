package com.github.jinahya.bit.io;

import java.nio.ByteBuffer;

/**
 * An abstract class for testing subclasses of {@link BufferByteInput}.
 */
abstract class BufferByteInputTest<T extends BufferByteInput<U>, U extends ByteBuffer>
        extends AbstractByteInputTest<T, U> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    BufferByteInputTest(final Class<T> byteInputClass, final Class<U> sourceClass) {
        super(byteInputClass, sourceClass);
    }
}
