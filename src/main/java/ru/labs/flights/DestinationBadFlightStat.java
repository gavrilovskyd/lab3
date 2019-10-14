package ru.labs.flights;

import java.io.Serializable;

public class DestinationBadFlightStat implements Serializable {
    private String from;
    private String to;
    private float maxDelay;
    private float delayedPercentage;
    private float canceledPercentage;

    public DestinationBadFlightStat(String from, String to, float maxDelay,
                                    float delayedPercentage, float canceledPercentage) {
        this.from = from;
        this.to = to;
        this.maxDelay = maxDelay;
        this.delayedPercentage = delayedPercentage;
        this.canceledPercentage = canceledPercentage;
    }

    @Override
    public String toString() {
        return String.format("%s,");
    }
}
