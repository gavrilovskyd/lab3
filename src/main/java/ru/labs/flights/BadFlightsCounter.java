package ru.labs.flights;

import java.io.Serializable;

public class BadFlightsCounter implements Serializable {
    private static final double EPS = 1e-6;

    private float maxDelay;
    private int delayedCount;
    private int canceledCount;
    private int totalCount;

    private BadFlightsCounter(float maxDelay, int delayedCount, int canceledCount, int totalCount) {
        this.maxDelay = maxDelay;
        this.delayedCount = delayedCount;
        this.canceledCount = canceledCount;
        this.totalCount = totalCount;
    }

    public BadFlightsCounter(String rawDelay, String rawCanceled) {
        this.maxDelay = 0.f;
        if (!rawDelay.isEmpty()) {
            this.maxDelay = Float.parseFloat(rawDelay);
        }

        this.canceledCount = (Float.parseFloat(rawCanceled) > EPS ? 1 : 0);
        this.delayedCount = (maxDelay > EPS ? 1 : 0);
        this.totalCount = 1;
    }

    public float getMaxDelay() {
        return maxDelay;
    }

    public int getDelayedCount() {
        return delayedCount;
    }

    public int getCanceledCount() {
        return canceledCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public float canceledPart() {
        return ((float) canceledCount / totalCount);
    }

    public float delayedPart() {
        return ((float) delayedCount / totalCount);
    }

    public static BadFlightsCounter add(BadFlightsCounter a, BadFlightsCounter b) {
        return new BadFlightsCounter(
                Math.max(a.getMaxDelay(), b.getMaxDelay()),
                a.getDelayedCount() + b.getDelayedCount(),
                a.getCanceledCount() + b.getCanceledCount(),
                a.getTotalCount()+b.getTotalCount()
        );
    }
}
