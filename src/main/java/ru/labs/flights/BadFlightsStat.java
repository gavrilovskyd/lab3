package ru.labs.flights;

import java.io.Serializable;

public class BadFlightsStat implements Serializable {
    private float maxDelay;
    private int delayedCount;
    private int canceledCount;
    private int totalCount;

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

    public double canceledPart() {
        return ((double) canceledCount / totalCount);
    }

    public double delayedPart() {
        return ((double) delayedCount / totalCount);
    }

    public BadFlightsStat(float maxDelay, int delayedCount, int canceledCount, int totalCount) {
        this.maxDelay = maxDelay;
        this.delayedCount = delayedCount;
        this.canceledCount = canceledCount;
        this.totalCount = totalCount;
    }

    public BadFlightsStat(String rawDelay, String rawCanceled) {
        
    }

    public static BadFlightsStat add(BadFlightsStat a, BadFlightsStat b) {
        return new BadFlightsStat(
                Math.max(a.getMaxDelay(), b.getMaxDelay()),
                a.getDelayedCount() + b.getDelayedCount(),
                a.getCanceledCount()+b.getCanceledCount(),
                a.getTotalCount()+b.getTotalCount()
        );
    }
}
