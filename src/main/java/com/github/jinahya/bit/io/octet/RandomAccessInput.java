/*
 * Copyright 2013 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.bit.io.octet;


import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * A {@link ByteInput} implementation for {@link RandomAccessFile}s.
 *
 * @see RandomAccessOutput
 */
public class RandomAccessInput extends AbstractByteInput<RandomAccessFile> {


    /**
     * Creates a new instance built on top of the specified input stream.
     *
     * @param source the source file or {@code null} if it's supposed to be
     * lazily initialized and set
     */
    public RandomAccessInput(final RandomAccessFile source) {

        super(source);
    }


    /**
     * {@inheritDoc} The {@code read()} method of {@code RandomAccessInput}
     * class invokes {@link RandomAccessFile#read()} on {@link #source} and
     * returns the result if it is not {@code -1}. Override this method if
     * {@link #source} is supposed to be lazily initialized and set.
     *
     * @return {@inheritDoc}
     *
     * @throws IOException {@inheritDoc}
     * @throws EOFException if the underlying file reached to end
     */
    @Override
    public int read() throws IOException {

        final int value = source.read();
        if (value == -1) {
            throw new EOFException();
        }

        return value;
    }

}
