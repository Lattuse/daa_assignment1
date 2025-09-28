package org.lattuse.algo;

import org.junit.jupiter.api.Test;
import org.lattuse.algo.geometry.ClosestPair;
import org.lattuse.algo.geometry.Point;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    public void testBruteForceAgreement() {
        Random rnd = new Random(123);
        int n = 300;
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
        double fast = ClosestPair.closestPair(pts);
        // brute-force
        double brute = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) brute = Math.min(brute, pts[i].distance(pts[j]));
        // allow tiny epsilon
        assertEquals(brute, fast, 1e-9);
    }
}

