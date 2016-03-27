/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.jinahya.bit.io;

import java.io.IOException;

/**
 * An interface for reading arbitrary length of bits.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface BitInput {

    /**
     * Reads a 1-bit boolean value. This method read a 1-bit unsigned int and
     * return {@code true} for {@code 1} and {@code false} for {@code 0}.
     *
     * @return {@code true} for {@code 1}, {@code false} for {@code 0}
     * @throws IOException if an I/O error occurs.
     */
    boolean readBoolean() throws IOException;

    /**
     * Reads a byte value.
     *
     * @param unsigned a flag for unsigned value.
     * @param size number of bits for value; between {@code 1} (inclusive) and
     * {@code 7 + (unsigned ? 0 : 1)} (inclusive)
     * @return a byte value
     * @throws IOException if an I/O error occurs.
     */
    byte readByte(boolean unsigned, int size) throws IOException;

    @Deprecated
    byte readUnsignedByte(int size) throws IOException;

    @Deprecated
    byte readByte(int size) throws IOException;

    /**
     * Reads a short value.
     *
     * @param unsigned a flag for unsigned value
     * @param size number of bits for value; between {@code 1} and
     * {@code 15 + delta} where the {@code delta} is {@code 0} for unsigned and
     * {@code 1} for signed
     * @return a short value
     * @throws IOException if an I/O error occurs.
     */
    short readShort(boolean unsigned, int size) throws IOException;

    @Deprecated
    short readUnsignedShort(int size) throws IOException;

    @Deprecated
    short readShort(int size) throws IOException;

    /**
     * Reads an int value.
     *
     * @param unsigned a flag for unsigned value
     * @param size number of bits for value ranged
     * {@code [1..(7 + unsigned ? 0 : 1)]}
     * @return an int value
     * @throws IOException if an I/O error occurs.
     */
    int readInt(boolean unsigned, int size) throws IOException;

    @Deprecated
    int readUnsignedInt(int size) throws IOException;

    @Deprecated
    int readInt(int size) throws IOException;

    /**
     * Reads a long value.
     *
     * @param unsigned a flag for unsigned value
     * @param size number of valid bits for value ranged
     * {@code [1..(15 + unsigned ? 0 : 1)]}
     * @return a long value
     * @throws IOException if an I/O error occurs.
     */
    long readLong(boolean unsigned, int size) throws IOException;

    @Deprecated
    long readUnsignedLong(int size) throws IOException;

    @Deprecated
    long readLong(int size) throws IOException;

    /**
     * Reads a char value.
     *
     * @param size the number of bits for value; between {@code 1} (inclusive)
     * and {@code 16} (inclusive)
     * @return a char value
     * @throws IOException if an I/O error occurs.
     */
    char readChar(int size) throws IOException;

    /**
     * Reads a float value. This method reads a signed 32-bit int value using
     * {@link #readInt(boolean, int)} and returns the value after converted with
     * {@link Float#intBitsToFloat(int)}.
     *
     * @return a float value
     * @throws IOException if an I/O error occurs.
     * @see #readInt(boolean, int)
     * @see Float#intBitsToFloat(int)
     * @deprecated Use {@link #readInt(boolean, int)} with {@code true} and
     * {@value java.lang.Integer#SIZE} and convert through
     * {@link Float#intBitsToFloat(int)}.
     */
    @Deprecated
    float readFloat() throws IOException;

    /**
     * Reads a double value. The method reads a signed 64-bit long value using
     * {@link #readLong(boolean, int)} and returns the value after converted
     * with {@link Double#longBitsToDouble(long)}.
     *
     * @return a double value
     * @throws IOException if an I/O error occurs.
     * @see #readLong(boolean, int)
     * @see Double#longBitsToDouble(long)
     * @deprecated Use {@link #readLong(boolean, int)} with {@code true} and
     * {@value java.lang.Long#SIZE} and convert through
     * {@link Double#longBitsToDouble(long)}
     */
    @Deprecated
    double readDouble() throws IOException;

//    /**
//     * Returns the number of bytes read so far.
//     *
//     * @return number of byte read so far.
//     */
//    long getCount();
//    /**
//     * Returns the bit index to read in current octet.
//     *
//     * @return bit index to read in current octet.
//     */
//    int getIndex();
    /**
     * Aligns to given number of bytes.
     *
     * @param bytes the number of bytes to align; must be positive.
     * @return the number of bits discarded while aligning
     * @throws IllegalArgumentException if {@code bytes} is less than {@code 1}.
     * @throws IOException if an I/O error occurs.
     */
    long align(int bytes) throws IOException;
}
