package org.lattuse.algo;

import org.junit.jupiter.api.Test;
import org.lattuse.algo.metrics.Metrics;
import org.lattuse.algo.select.DeterministicSelect;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

    @Test
    public void testSelectRandom() {
        Random rnd = new Random(42);
        int[] a = rnd.ints(500, -10000, 10000).toArray();
        int k = 200;
        int[] copy = a.clone();
        Arrays.sort(copy);
        Metrics m = new Metrics();
        int val = DeterministicSelect.select(a, k, m);
        assertEquals(copy[k], val);
    }
}
