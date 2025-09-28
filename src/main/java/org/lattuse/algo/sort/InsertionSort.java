package org.lattuse.algo.sort;

import org.lattuse.algo.metrics.Metrics;

public class InsertionSort {
    public static void sort(int[] a, int lo, int hi, Metrics m) {
        if (a == null || lo >= hi) return;
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.swaps++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
            m.swaps++;
        }
    }

    public static void sort(int[] a, Metrics m) {
        if (a == null) return;
        sort(a, 0, a.length - 1, m);
    }
}

