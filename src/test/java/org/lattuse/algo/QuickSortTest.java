package org.lattuse.algo;

import org.junit.jupiter.api.Test;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.sort.QuickSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testRandomSmall() {
        Random rnd = new Random(123);
        int[] a = rnd.ints(200, -1000, 1000).toArray();
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, rnd);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testSortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, new Random(1));
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testReverseArray() {
        int[] a = {5, 4, 3, 2, 1};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, new Random(1));
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testAllEqualElements() {
        int[] a = new int[50];
        Arrays.fill(a, 7);
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, new Random(1));
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testSingleElement() {
        int[] a = {42};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, new Random(1));
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testEmptyArray() {
        int[] a = {};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        QuickSort.sort(a, m, new Random(1));
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }
}

