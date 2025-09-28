package org.lattuse.algo;

import org.lattuse.algo.geometry.ClosestPair;
import org.lattuse.algo.geometry.Point;
import org.lattuse.algo.metrics.CsvWriter;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.select.DeterministicSelect;
import org.lattuse.algo.sort.MergeSort;
import org.lattuse.algo.sort.QuickSort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        // default values
        String algo = "all";
        int minN = 100;
        int maxN = 1000;
        int step = 100;
        int runs = 3;
        long seed = 42;

        // simple parse of args (--algo=mergesort ...)
        for (String arg : args) {
            if (arg.startsWith("--algo=")) algo = arg.substring(7);
            else if (arg.startsWith("--minN=")) minN = Integer.parseInt(arg.substring(7));
            else if (arg.startsWith("--maxN=")) maxN = Integer.parseInt(arg.substring(7));
            else if (arg.startsWith("--step=")) step = Integer.parseInt(arg.substring(7));
            else if (arg.startsWith("--runs=")) runs = Integer.parseInt(arg.substring(7));
            else if (arg.startsWith("--seed=")) seed = Long.parseLong(arg.substring(7));
        }

        try (CsvWriter writer = new CsvWriter("results/results.csv", false)) {
            writer.writeLine("algo,n,run,elapsed_ns,comparisons,swaps,max_depth");

            for (int n = minN; n <= maxN; n += step) {
                for (int r = 1; r <= runs; r++) {
                    Random rnd = new Random(seed + r);
                    if (algo.equals("mergesort") || algo.equals("all"))
                        runMergeSort(n, r, rnd, writer);
                    if (algo.equals("quicksort") || algo.equals("all"))
                        runQuickSort(n, r, rnd, writer);
                    if (algo.equals("select") || algo.equals("all"))
                        runSelect(n, r, rnd, writer);
                    if (algo.equals("closest") || algo.equals("all"))
                        runClosest(n, r, rnd, writer);
                }
            }
        }
    }

    private static void runMergeSort(int n, int run, Random rnd, CsvWriter writer) throws IOException {
        int[] arr = rnd.ints(n, -1000000, 1000000).toArray();
        Metrics m = new Metrics();
        long t0 = System.nanoTime();
        MergeSort.sort(arr, m);
        long elapsed = System.nanoTime() - t0;
        writer.writeLine(String.format("mergesort,%d,%d,%d,%d,%d,%d",
                n, run, elapsed, m.comparisons, m.swaps, m.maxDepth));
    }

    private static void runQuickSort(int n, int run, Random rnd, CsvWriter writer) throws IOException {
        int[] arr = rnd.ints(n, -1000000, 1000000).toArray();
        Metrics m = new Metrics();
        long t0 = System.nanoTime();
        QuickSort.sort(arr, m, rnd);
        long elapsed = System.nanoTime() - t0;
        writer.writeLine(String.format("quicksort,%d,%d,%d,%d,%d,%d",
                n, run, elapsed, m.comparisons, m.swaps, m.maxDepth));
    }

    private static void runSelect(int n, int run, Random rnd, CsvWriter writer) throws IOException {
        int[] arr = rnd.ints(n, -1000000, 1000000).toArray();
        int k = n / 2; // find median))
        Metrics m = new Metrics();
        long t0 = System.nanoTime();
        int val = DeterministicSelect.select(arr, k, m);
        long elapsed = System.nanoTime() - t0;
        writer.writeLine(String.format("select,%d,%d,%d,%d,%d,%d",
                n, run, elapsed, m.comparisons, m.swaps, m.maxDepth));
    }

    private static void runClosest(int n, int run, Random rnd, CsvWriter writer) throws IOException {
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
        }
        long t0 = System.nanoTime();
        double d = ClosestPair.closestPair(pts);
        long elapsed = System.nanoTime() - t0;
        // comparisons/swaps for ClosestPair, well, could be 0 I suppose
        writer.writeLine(String.format("closest,%d,%d,%d,%d,%d,%d",
                n, run, elapsed, 0, 0, 0));
    }
}

