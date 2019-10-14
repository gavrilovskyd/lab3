package ru.labs.flights;

import java.io.Serializable;

public class DestinationBadFlightStat implements Serializable {
    private String from;
    private String to;
    private float maxDelay;
    private float delayedPercentage;
}
