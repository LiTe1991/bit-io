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

package com.github.jinahya.bit.io.codec;


import com.github.jinahya.bit.io.BitInput;
import java.io.IOException;


/**
 * An abstract class for implementing {@code BitCodec}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T> value type parameter
 */
public abstract class NullableDecoder<T> extends Nullable
    implements BitDecoder<T> {


    /**
     * Creates a new instance.
     *
     * @param nullable a flag for nullability of the value.
     */
    public NullableDecoder(final boolean nullable) {

        super(nullable);
    }


    /**
     * {@inheritDoc} This method optionally (by the value of {@link #nullable})
     * decodes additional 1-bit boolean and, if the value need to be decoded,
     * invokes {@link #decodeValue(com.github.jinahya.bit.io.BitInput)} with
     * given {@code input}.
     *
     * @param input {@inheritDoc}
     *
     * @return {@inheritDoc}
     *
     * @throws IOException {@inheritDoc}
     */
    @Override
    public T decode(final BitInput input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (nullable && !input.readBoolean()) {
            return null;
        }

        return decodeValue(input);
    }


    /**
     * Decodes value from given input. This method is supposed to return a
     * non-null value.
     *
     * @param input the input
     *
     * @return decoded value; should not be {@code null}.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected abstract T decodeValue(final BitInput input) throws IOException;

}

