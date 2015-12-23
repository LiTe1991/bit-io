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


package com.github.jinahya.bit.io;


import java.io.IOException;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ArrayInput extends AbstractByteInput<byte[]> {


    public ArrayInput(final byte[] source, final int index, final int limit) {

        super(source);

        this.index = index;
        this.limit = limit;
    }


    @Override
    public int readUnsignedByte() throws IOException {

        if (index >= limit) {
            throw new IndexOutOfBoundsException();
        }

        return source[index++] & 0xFF;
    }


    /**
     * Replaces the value of {@link #source} with given and returns this.
     *
     * @param target new value of {@link #source}.
     *
     * @return this instance.
     */
    public ArrayInput source(final byte[] target) {

        setSource(target);

        return this;
    }


    public int getIndex() {

        return index;
    }


    public void setIndex(int index) {

        this.index = index;
    }


    public ArrayInput index(final int index) {

        setIndex(index);

        return this;
    }


    public int getLimit() {

        return limit;
    }


    public void setLimit(int limit) {

        this.limit = limit;
    }


    public ArrayInput limit(final int limit) {

        setLimit(limit);

        return this;
    }


    /**
     * The index in the {@link #source} to read.
     */
    protected int index;


    /**
     * The position in the {@link #source} which {@link #index} can't exceed.
     */
    protected int limit;

}

