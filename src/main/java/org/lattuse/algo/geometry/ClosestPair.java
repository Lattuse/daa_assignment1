package org.lattuse.algo.geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class ClosestPair {

    public static double closestPair(Point[] points) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return sweep(pts);
    }

    private static double sweep(Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        TreeSet<Point> box = new TreeSet<>(Comparator.<Point>comparingDouble(p -> p.y)
                .thenComparingDouble(p -> p.x));
        int left = 0;
        for (Point p : pts) {
            while (left < pts.length && p.x - pts[left].x > best) {
                box.remove(pts[left]);
                left++;
            }
            // iterate over points in box within y-range [p.y - best, p.y + best]
            Point lower = new Point(-Double.MAX_VALUE, p.y - best);
            Point upper = new Point(Double.MAX_VALUE, p.y + best);
            for (Iterator<Point> it = box.subSet(lower, true, upper, true).iterator(); it.hasNext(); ) {
                Point q = it.next();
                double dist = p.distance(q);
                if (dist < best) best = dist;
            }
            box.add(p);
        }
        return best;
    }
}

