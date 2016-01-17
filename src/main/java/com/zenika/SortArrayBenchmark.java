/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.zenika;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;

public class SortArrayBenchmark {

    @Benchmark
    public int[] benchmark(ArrayState d) {
        Arrays.sort(d.getArrayToSort());
        return d.getArrayToSort();
    }

    @State(Scope.Benchmark)
    public static class ArrayState {
        @Param({"10000", "100000", "1000000"})
        int arraySize;
        int[] shuffledArray;
        int[] arrayToSort;

        @Setup(Level.Trial)
        public void init() {
            shuffledArray = new int[arraySize];
            arrayToSort = new int[arraySize];
            Random random = new Random();
            for (int i : shuffledArray) {
                shuffledArray[i] = random.nextInt(500);
            }
        }

        // Before/after every method call (this level is not recommended until you know what you are doing)
        @Setup(Level.Invocation)
        public void copy() {
            arrayToSort = shuffledArray.clone();
        }

        int[] getArrayToSort() {
            return arrayToSort;
        }
    }

}
