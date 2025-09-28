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
                SortUtils.swap(a, pivotIndex, hi, m);

                int p = SortUtils.partition(a, lo, hi, m);

                int leftSize = p - lo;
                int rightSize = hi - p;
                if (leftSize < rightSize) {
                    quicksort(a, lo, p - 1, m, rnd);
                    lo = p + 1;
                } else {
                    quicksort(a, p + 1, hi, m, rnd);
                    hi = p - 1;
                }
            } finally {
                m.exitRecursion();
            }
        }
    }
}

