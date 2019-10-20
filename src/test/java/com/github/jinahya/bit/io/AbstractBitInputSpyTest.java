package com.github.jinahya.bit.io;

/*-
 * #%L
 * bit-io
 * %%
 * Copyright (C) 2014 - 2019 Jinahya, Inc.
 * %%
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
 * #L%
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({MockitoExtension.class})
@Slf4j
public class AbstractBitInputSpyTest {

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void stubRead() throws IOException {
        //when(bitInput.read()).thenReturn(current().nextInt(0, 256));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link AbstractBitInput#readSignedByte(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadSignedByte() throws IOException {
        final int size = current().nextInt(1, Byte.SIZE + 1);
        final byte value = bitInput.readSignedByte(size);
        if (value >= 0) {
            assertEquals(0, value >> size);
        } else {
            assertEquals(-1, value >> (size - 1));
        }
    }

    /**
     * Tests {@link AbstractBitInput#readUnsignedByte(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadUnsignedByte() throws IOException {
        final int size = current().nextInt(1, Byte.SIZE);
        final byte value = bitInput.readUnsignedByte(size);
        assertTrue(value >= 0);
        assertEquals(0, value >> size);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link AbstractBitInput#readSignedShort(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadSignedShort() throws IOException {
        final int size = current().nextInt(1, Short.SIZE + 1);
        final short value = bitInput.readSignedShort(size);
        if (value >= 0) {
            assertEquals(0, value >> size);
        } else {
            assertEquals(-1, value >> (size - 1));
        }
    }

    /**
     * Tests {@link AbstractBitInput#readUnsignedShort(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadUnsignedShort() throws IOException {
        final int size = current().nextInt(1, Short.SIZE);
        final short value = bitInput.readUnsignedShort(size);
        assertTrue(value >= 0);
        assertEquals(0, value >> size);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link AbstractBitInput#readSignedInt(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadSignedInt() throws IOException {
        final int size = current().nextInt(1, Integer.SIZE + 1);
        final int value = bitInput.readSignedInt(size);
        if (value >= 0) {
            assertEquals(0, value >> size);
        } else {
            assertEquals(-1, value >> (size - 1));
        }
    }

    /**
     * Tests {@link AbstractBitInput#readUnsignedInt(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadUnsignedInt() throws IOException {
        final int size = current().nextInt(1, Integer.SIZE);
        final int value = bitInput.readUnsignedInt(size);
        assertTrue(value >= 0);
        assertEquals(0, value >> size);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link AbstractBitInput#readSignedLong(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadSignedLong() throws IOException {
        final int size = current().nextInt(1, Integer.SIZE + 1);
        final int value = bitInput.readSignedInt(size);
        if (value >= 0) {
            assertEquals(0, value >> size);
        } else {
            assertEquals(-1, value >> (size - 1));
        }
    }

    /**
     * Tests {@link AbstractBitInput#readUnsignedLong(int)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @RepeatedTest(8)
    public void testReadUnsignedLong() throws IOException {
        final int size = current().nextInt(1, Integer.SIZE);
        final int value = bitInput.readUnsignedInt(size);
        assertTrue(value >= 0);
        assertEquals(0, value >> size);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @RepeatedTest(8)
    public void testReadChar() throws IOException {
        final int size = current().nextInt(1, Character.SIZE + 1);
        final char value = bitInput.readChar(size);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Spy
    private AbstractBitInput bitInput;
}
