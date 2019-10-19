package ru.labs.flights;

import java.io.Serializable;

public class DestinationBadFlightStat implements Serializable {
    private String from;
    private String to;
    private float maxDelay;
    private float delayedPercentage;
    private float canceledPercentage;

    public DestinationBadFlightStat(String from, String to, float maxDelay,
                                    float delayedPart, float canceledPart) {
        this.from = from;
        this.to = to;
        this.maxDelay = maxDelay;
        this.delayedPercentage = delayedPart * 100;
        this.canceledPercentage = canceledPart * 100;
    }

    @Override
    public String toString() {
        return String.format("\"%s\",\"%s\",\"%.2f\",\"%.2f%%\",\"%.2f%%\"",
                from,to,maxDelay,delayedPercentage,canceledPercentage);
    }
}
