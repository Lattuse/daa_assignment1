package org.lattuse.metrics;

public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    private int currentDepth = 0;
    public int maxDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() {
        if (currentDepth > 0) currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        currentDepth = 0;
        maxDepth = 0;
    }

    public String toCsvFields() {
        return comparisons + "," + swaps + "," + maxDepth;
    }
}
