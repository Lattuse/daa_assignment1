package org.lattuse.algo.sort;

import org.lattuse.algo.metrics.Metrics;

import java.util.Arrays;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        m.reset();
        int[] aux = new int[a.length];
        mergesort(a, aux, 0, a.length - 1, m);
    }

    private static void mergesort(int[] a, int[] aux, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            InsertionSort.sort(a, lo, hi, m);
            return;
        }

        m.enterRecursion();
        try {
            int mid = lo + (hi - lo) / 2;
            mergesort(a, aux, lo, mid, m);
            mergesort(a, aux, mid + 1, hi, m);

            if (a[mid] <= a[mid + 1]) {
                // optimization, just return if already sorted
                if (m != null) m.comparisons++;
                return;
            }

            merge(a, aux, lo, mid, hi, m);
        } finally {
            m.exitRecursion();
        }
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Metrics m) {
        // copy in buffer
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else {
                if (m != null) m.comparisons++;
                if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                } else {
                    a[k] = aux[i++];
                }
            }
        }
    }
}


