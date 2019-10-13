package ru.labs.flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class DelayStatApp {
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightLines = sc.textFile("flights_data.csv");
        JavaPairRDD<Tuple2<String, String>, FlightInfo> airportsDelay =
                flightLines.mapToPair()
    }
}
