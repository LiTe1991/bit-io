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
import org.junit.jupiter.params.provider.Arguments;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.lang.System.arraycopy;
import static java.nio.ByteBuffer.allocate;
import static java.util.Arrays.copyOf;

@Slf4j
final class ByteIoTests {

    // -----------------------------------------------------------------------------------------------------------------
    static <R> R applyByteIoArray(final BiFunction<? super ByteOutput, ? super ByteInput, ? extends R> function) {
        final byte[][] holder = new byte[][] {
                new byte[16]
        };
        final ByteOutput output = new ArrayByteOutput(holder[0]) {
            @Override
            public void write(int value) throws IOException {
                if (index == target.length) {
                    target = copyOf(target, target.length << 1);
                    holder[0] = target;
                }
                super.write(value);
            }
        };
        final ByteInput input = new ArrayByteInput(null) {
            @Override
            public int read() throws IOException {
                if (source == null) {
                    source = holder[0];
                    index = 0;
                }
                return super.read();
            }
        };
        return function.apply(output, input);
    }

    static Stream<Arguments> sourceBitIoBuffer() {
        final List<ByteBuffer> list = new ArrayList<>();
        list.add(allocate(16));
        final BitOutput bo = new DefaultBitOutput(new BufferByteOutput(null) {
            @Override
            public void write(int value) throws IOException {
                if (target == null) {
                    target = list.get(0);
                }
                if (!target.hasRemaining()) {
                    final ByteBuffer bigger = allocate(target.capacity() << 1);
                    if (target.hasArray() && bigger.hasArray()) {
                        arraycopy(target.array(), target.arrayOffset(), bigger.array(), target.arrayOffset(),
                                  target.position());
                    } else {
                        for (target.flip(); target.hasRemaining(); ) {
                            bigger.put(target.get());
                        }
                    }
                    target = bigger;
                    list.set(0, bigger);
                }
                super.write(value);
            }
        });
        final BitInput bi = new DefaultBitInput(new BufferByteInput(null) {
            @Override
            public int read() throws IOException {
                if (source == null) {
                    source = list.get(0);
                    source.flip();
                }
                return super.read();
            }
        });
        return Stream.of(Arguments.of(bo, bi));
    }

    static Stream<Arguments> sourceBitIoData() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new DefaultBitOutput(new DataByteOutput(null) {
            @Override
            public void write(final int value) throws IOException {
                if (target == null) {
                    target = new DataOutputStream(baos);
                }
                super.write(value);
            }
        });
        final BitInput bi = new DefaultBitInput(new DataByteInput(null) {
            @Override
            public int read() throws IOException {
                if (source == null) {
                    source = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
                }
                return super.read();
            }
        });
        return Stream.of(Arguments.of(bo, bi));
    }

    static Stream<Arguments> sourceBitIoStream() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new DefaultBitOutput(new StreamByteOutput(baos));
        final BitInput bi = new DefaultBitInput(new StreamByteInput(null) {
            @Override
            public int read() throws IOException {
                if (getSource() == null) {
                    setSource(new ByteArrayInputStream(baos.toByteArray()));
                }
                return super.read();
            }
        });
        return Stream.of(Arguments.of(bo, bi));
    }

    static Stream<Arguments> sourceBitIo() {
        return Stream.of(sourceBitIoArray(), sourceBitIoBuffer(), sourceBitIoData(), sourceBitIoStream())
                .flatMap(s -> s);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ByteIoTests() {
        super();
    }
}
