package ru.labs.flights;

import org.apache.spark.SparkConf;

public class DelayStatApp {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
    }
}
