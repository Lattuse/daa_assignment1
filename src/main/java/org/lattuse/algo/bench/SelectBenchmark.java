package org.lattuse.algo.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.select.DeterministicSelect;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)       // измеряем среднее время
@OutputTimeUnit(TimeUnit.MICROSECONDS) // в микросекундах
@State(Scope.Thread)
public class SelectBenchmark {

    @Param({"1000", "10000", "100000"}) // разные размеры массива
    private int n;

    private int[] data;
    private int k;
    private Random rnd;

    @Setup(Level.Iteration)
    public void setup() {
        rnd = new Random(42);
        data = rnd.ints(n, -1_000_000, 1_000_000).toArray();
        k = rnd.nextInt(n);
    }

    @Benchmark
    public void testDeterministicSelect(Blackhole bh) {
        int[] copy = data.clone();
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(copy, k, m);
        bh.consume(val);
    }

    @Benchmark
    public void testArraysSort(Blackhole bh) {
        int[] copy = data.clone();
        Arrays.sort(copy);
        int val = copy[k];
        bh.consume(val);
    }
}
