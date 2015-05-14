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


import com.google.inject.AbstractModule;
import java.io.IOException;


/**
 *
 * @author Jin Kwon
 */
public class ByteInputModule extends AbstractModule {


    private static class MockedByteInput implements ByteInput {


        @Override
        public int readUnsignedByte() throws IOException {

            return (int) (System.currentTimeMillis() & 0xFF);
        }


    }


    @Override
    protected void configure() {
        bind(ByteInput.class).to(MockedByteInput.class);
    }


}
