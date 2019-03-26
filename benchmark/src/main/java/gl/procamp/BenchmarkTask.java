/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package gl.procamp;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class BenchmarkTask {

    private final int size = 100;

    @Benchmark
    public void tuneThrowingExceptionWithStackTrace() {
        throw new CustomExceptionWithStackTrace("with stackTrace");

    }

    @Benchmark
    public void tuneThrowingExceptionWithoutStackTrace() {
        throw new CustomRuntimeExceptionWithoutStachTrace("without stackTrace");
    }

    @Benchmark
    public void tuneLoopOperation() {
        List<Integer> listValues = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            listValues.add(i);
        }
        System.out.println(listValues.get(0));
    }

    @Benchmark
    public void tuneStreamOperation() {
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).boxed().collect(Collectors.toList());
        System.out.println(listValues.get(0));
    }

    @Benchmark
    public void tuneStreamParallelOperation(){
        List<Integer> listValues = IntStream.rangeClosed(1, this.size).parallel().boxed().collect(Collectors.toList());
        System.out.println(listValues.get(0));
    }
}