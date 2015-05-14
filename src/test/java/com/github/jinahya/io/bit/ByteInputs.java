/*
 * Copyright 2014 Jin Kwon.
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


package com.github.jinahya.io.bit;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.mockito.Mockito;


/**
 *
 * @author Jin Kwon
 */
public class ByteInputs {


    public static ByteInput lambda(final InputStream source) {

        return () -> source.read();
    }


    public static ByteInput lambda(final ByteBuffer source) {

        return () -> source.get() & 0xFF;
    }


    public static ByteInput mock() throws IOException {

        final ByteInput mock = Mockito.mock(ByteInput.class);

        Mockito.when(mock.readUnsignedByte())
                .thenReturn((int) (System.currentTimeMillis() & 0xFF));

        return mock;
    }


}
