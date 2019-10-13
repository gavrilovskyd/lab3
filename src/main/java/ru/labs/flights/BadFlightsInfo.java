package ru.labs.flights;

import java.io.Serializable;

public class BadFlightsInfo implements Serializable {
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

    public double elayedPart() {
        
    }
}
