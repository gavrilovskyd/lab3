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
        this.totalCount = canceledCount;
    }

    public static BadFlightsStat add(BadFlightsStat a, BadFlightsStat b) {
        return new BadFlightsStat(
                Math.max(a.maxDelay, b.maxDelay);
        )
    }
}
