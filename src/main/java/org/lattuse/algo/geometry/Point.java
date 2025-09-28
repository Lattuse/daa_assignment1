package org.lattuse.algo.geometry;

public class Point {
    public final double x;
    public final double y;
    public Point(double x, double y) { this.x = x; this.y = y; }

    public double distance(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.hypot(dx, dy);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
