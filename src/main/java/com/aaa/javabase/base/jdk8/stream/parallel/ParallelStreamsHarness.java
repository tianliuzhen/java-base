package com.aaa.javabase.base.jdk8.stream.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author liuzhen.tian
 * @version 1.0 ParallelStreamsHarness.java  2020/12/25 13:16
 */
public class ParallelStreamsHarness {
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs" );
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        System.out.println("Parallel range forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs" );
    }

    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


    public static class ParallelStreams {

        public static long iterativeSum(long n) {
            long result = 0;
            for (long i = 0; i <= n; i++) {
                result += i;
            }
            return result;
        }

        public static long sequentialSum(long n) {
            return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
        }

        public static long parallelSum(long n) {
            return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
        }

        public static long rangedSum(long n) {
            return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
        }

        public static long parallelRangedSum(long n) {
            return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
        }
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
