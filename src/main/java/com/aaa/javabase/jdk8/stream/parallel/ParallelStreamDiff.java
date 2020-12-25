package com.aaa.javabase.jdk8.stream.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author liuzhen.tian
 * @version 1.0 ParallelStreamsHarness.java  2020/12/25 13:16
 */
public class ParallelStreamDiff {
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        System.out.println("并行计算 = " + ParallelStreams.sideEffectParallelSum(10_000_000L));
        System.out.println("======");
        System.out.println("顺序计算 = " + ParallelStreams.sideEffectSum(10_000_000L));
    }


    public static class ParallelStreams {


        public static long sideEffectSum(long n) {
            Accumulator accumulator = new Accumulator();
            LongStream.rangeClosed(1, n).forEach(accumulator::add);
            return accumulator.total;
        }

        public static long sideEffectParallelSum(long n) {
            Accumulator accumulator = new Accumulator();
            LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
            return accumulator.total;
        }

    }
    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
