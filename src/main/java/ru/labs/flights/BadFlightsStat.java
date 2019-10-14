package ru.labs.flights;

import java.io.Serializable;

public class BadFlightsStat implements Serializable {
    private static final double EPS = 1e-6;

    private float maxDelay;
    private int delayedCount;
    private int canceledCount;
    private int totalCount;

    private BadFlightsStat(float maxDelay, int delayedCount, int canceledCount, int totalCount) {
        this.maxDelay = maxDelay;
        this.delayedCount = delayedCount;
        this.canceledCount = canceledCount;
        this.totalCount = totalCount;
    }

    public BadFlightsStat(String rawDelay, String rawCanceled) {
        this.originAirportName = "";
        this.destAirportName = "";

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

    public String getOriginAirportName() {
        return originAirportName;
    }

    public String getDestAirportName() {
        return destAirportName;
    }

    public void setOriginAirportName(String originAirportName) {
        this.originAirportName = originAirportName;
    }

    public void setDestAirportName(String destAirportName) {
        this.destAirportName = destAirportName;
    }

    public float canceledPart() {
        return ((float) canceledCount / totalCount);
    }

    public float delayedPart() {
        return ((float) delayedCount / totalCount);
    }

    public static BadFlightsStat add(BadFlightsStat a, BadFlightsStat b) {
        return new BadFlightsStat(
                Math.max(a.getMaxDelay(), b.getMaxDelay()),
                a.getDelayedCount() + b.getDelayedCount(),
                a.getCanceledCount() + b.getCanceledCount(),
                a.getTotalCount()+b.getTotalCount()
        );
    }
}
