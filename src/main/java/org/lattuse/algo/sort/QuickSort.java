package org.lattuse.algo.sort;

import org.lattuse.algo.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m, Random rnd) {
        if (a == null || a.length < 2) return;
        m.reset();
        quicksort(a, 0, a.length - 1, m, rnd);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m, Random rnd) {
        while (lo < hi) {
            if (hi - lo + 1 <= CUTOFF) {
                InsertionSort.sort(a, lo, hi, m);
                return;
            }
            m.enterRecursion();
            try {
                int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
                swap(a, pivotIndex, hi, m);
                int p = partition(a, lo, hi, m);
                int leftSize = p - lo;
                int rightSize = hi - p;
                if (leftSize < rightSize) {
                    quicksort(a, lo, p - 1, m, rnd);
                    lo = p + 1; // tail recurse on larger part iteratively
                } else {
                    quicksort(a, p + 1, hi, m, rnd);
                    hi = p - 1;
                }
            } finally {
                m.exitRecursion();
            }
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.comparisons++;
            if (a[j] <= pivot) {
                swap(a, i++, j, m);
            }
        }
        swap(a, i, hi, m);
        return i;
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        m.swaps++;
    }
}
