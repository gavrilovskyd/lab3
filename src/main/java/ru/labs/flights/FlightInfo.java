package ru.labs.flights;

import java.io.Serializable;

public class FlightInfo implements Serializable {
    private float delay;
    private boolean isCanceled;
    private static final double EPS = 1e-6;

    public float getDelay() {
        return delay;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public FlightInfo(float delay, float isCanceled) {
        this.delay = delay;
        this.isCanceled = (isCanceled > EPS);
    }
}
