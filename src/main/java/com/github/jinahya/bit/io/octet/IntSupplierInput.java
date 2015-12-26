/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


import java.io.IOException;
import java.util.function.IntSupplier;


/**
 * A {@link ByteInput} implementation uses a {@link IntSupplier} instance.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IntSupplierInput extends AbstractByteInput<IntSupplier> {


    /**
     * Creates a new instance with specified {@code supplier} which is mapped to
     * {@link #source}.
     *
     * @param source the supplier instance or {@code null} if it's supposed to
     * be lazily initialized and set
     */
    public IntSupplierInput(final IntSupplier source) {

        super(source);
    }


    /**
     * {@inheritDoc} The {@code read()} method of {@code IntSupplierInput}
     * invokes {@link IntSupplier#getAsInt()} on {@link #source} and returns the
     * result. Override this method if {@link #source} is supposed to lazily
     * initialized and set.
     *
     * @return {@inheritDoc}
     *
     * @throws IOException {@inheritDoc}
     */
    @Override
    public int read() throws IOException {

        return source.getAsInt();
    }

}
