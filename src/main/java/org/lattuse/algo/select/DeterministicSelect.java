package org.lattuse.algo.select;

import org.lattuse.algo.metrics.Metrics;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] a, int k, Metrics m) {
        if (a == null || k < 0 || k >= a.length) throw new IllegalArgumentException("k out of bounds");
        m.reset();
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        if (lo == hi) return a[lo];
        int n = hi - lo + 1;
        if (n <= 10) {
            Arrays.sort(a, lo, hi + 1);
            return a[k];
        }

        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int gLo = lo + i * 5;
            int gHi = Math.min(gLo + 4, hi);
            insertionSort(a, gLo, gHi, m);
            int medianIndex = gLo + (gHi - gLo) / 2;
            swap(a, lo + i, medianIndex, m);
        }

        int midOfMediansIndex = lo + (numGroups - 1) / 2;
        int pivotValue = select(a, lo, lo + numGroups - 1, midOfMediansIndex, m);

        // 3-way partition around pivotValue
        int[] lr = partition3(a, lo, hi, pivotValue, m);
        int left = lr[0], right = lr[1];

        if (k >= left && k <= right) {
            return a[left]; // pivotValue (all equals between left..right)
        } else if (k < left) {
            return select(a, lo, left - 1, k, m);
        } else {
            return select(a, right + 1, hi, k, m);
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.swaps++;
                    j--;
                } else break;
            }
            a[j + 1] = key;
            m.swaps++;
        }
    }

    private static int[] partition3(int[] a, int lo, int hi, int pivotValue, Metrics m) {
        int lt = lo, i = lo, gt = hi;
        while (i <= gt) {
            m.comparisons++;
            if (a[i] < pivotValue) {
                swap(a, lt++, i++, m);
            } else if (a[i] > pivotValue) {
                swap(a, i, gt--, m);
            } else {
                i++;
            }
        }
        return new int[] { lt, gt };
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        m.swaps++;
    }
}

