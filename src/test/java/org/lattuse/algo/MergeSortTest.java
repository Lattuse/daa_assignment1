package org.lattuse.algo;

import org.junit.jupiter.api.Test;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.sort.MergeSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}


