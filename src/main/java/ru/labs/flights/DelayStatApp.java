package ru.labs.flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class DelayStatApp {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        
    }
}
