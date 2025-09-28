package org.lattuse.algo;

import org.junit.jupiter.api.Test;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.sort.MergeSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortTest {

    @Test
    public void testRandomSmall() {
        Random rnd = new Random(123);
        int[] a = rnd.ints(200, -1000, 1000).toArray();
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testSortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testReverseArray() {
        int[] a = {5, 4, 3, 2, 1};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testAllEqualElements() {
        int[] a = new int[50];
        Arrays.fill(a, 7);
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testSingleElement() {
        int[] a = {42};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testEmptyArray() {
        int[] a = {};
        int[] copy = a.clone();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }

    @Test
    public void testRecursionDepthBound() {
        int n = 1024;
        int[] a = new Random(42).ints(n, -1000, 1000).toArray();
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        int log2n = (int) (Math.log(n) / Math.log(2));
        assertTrue(m.maxDepth <= log2n + 2, "Recursion depth too large");
    }
}



