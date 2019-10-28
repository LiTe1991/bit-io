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

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitStackTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testPushAssertStackOverflow() throws ReflectiveOperationException {
        final BitStack stack = new BitStack();
        final Field field = BitStack.class.getDeclaredField("top");
        field.setAccessible(true);
        field.set(stack, Integer.MAX_VALUE);
        assertThrows(RuntimeException.class,
                     () -> stack.push(current().nextInt(1, Long.SIZE), current().nextLong() >>> 1));
    }

    @Test
    void testPush() {
        final BitStack stack = new BitStack();
        stack.push(Long.SIZE - 1, current().nextLong() >>> 1);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testPopAssertStackUnderflow() {
        final BitStack stack = new BitStack();
        assertThrows(RuntimeException.class, () -> stack.pop(current().nextInt(1, Long.SIZE)));
    }

    @Test
    void testPop() {
        final BitStack stack = new BitStack();
        final int size = current().nextInt(1, Long.SIZE);
        final long value = current().nextLong() >>> 1;
        stack.push(size, value);
        assertTrue(stack.pop(current().nextInt(1, size + 1)) <= value);
    }
}