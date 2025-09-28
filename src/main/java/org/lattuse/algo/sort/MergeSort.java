package org.lattuse.algo.sort;

import org.lattuse.algo.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 32;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        int[] buf = new int[a.length];
        m.reset();
        sort(a, buf, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            InsertionSort.sort(a, lo, hi, m);
            return;
        }
        m.enterRecursion();
        try {
            int mid = lo + (hi - lo) / 2;
            sort(a, buf, lo, mid, m);
            sort(a, buf, mid + 1, hi, m);
            merge(a, buf, lo, mid, hi, m);
        } finally {
            m.exitRecursion();
        }
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            m.comparisons++;
            if (a[i] <= a[j]) {
                buf[k++] = a[i++];
            } else {
                buf[k++] = a[j++];
            }
            m.swaps++; // counting copies as "swaps/assignments"
        }
        while (i <= mid) {
            buf[k++] = a[i++];
            m.swaps++;
        }
        while (j <= hi) {
            buf[k++] = a[j++];
            m.swaps++;
        }
        // copy back
        for (int t = lo; t <= hi; t++) {
            a[t] = buf[t];
            m.swaps++;
        }
    }
}

