package org.lattuse.algo.sort;

import org.lattuse.algo.metrics.Metrics;

import java.util.Random;

public class SortUtils {

    //swap
    public static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        if (m != null) m.swaps++;
    }

    //partition
    public static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (m != null) m.comparisons++;
            if (a[j] <= pivot) {
                swap(a, i++, j, m);
            }
        }
        swap(a, i, hi, m);
        return i;
    }

    //shuffle
    public static void shuffle(int[] a, Random rnd) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    //guard
    public static void guardRange(int[] a, int lo, int hi) {
        if (a == null) throw new IllegalArgumentException("Array is null");
        if (lo < 0 || hi >= a.length || lo > hi) {
            throw new IllegalArgumentException("Invalid range: " + lo + ".." + hi);
        }
    }
}

