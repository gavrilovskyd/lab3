package ru.labs.flights;

import java.io.Serializable;

public class BadFlightsInfo implements Serializable {
    private float maxDelay;
    private int delayedCount;
    private int canceledCount;
    private int totalCount;

    private boolean isCanceled;
    private static final double EPS = 1e-6;

    public float getDelay() {
        return delay;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public BadFlightsInfo(float delay, float isCanceled) {
        this.delay = delay;
        this.isCanceled = (isCanceled > EPS);
    }
}
