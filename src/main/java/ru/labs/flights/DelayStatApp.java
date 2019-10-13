package ru.labs.flights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class DelayStatApp {
    private static final int ORIGIN_AIRPORT_ID_FILED = 11;
    private static final int DEST_AIRPORT_ID_FILED = 14;

    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightLines = sc.textFile("flights_data.csv");
        JavaPairRDD<Tuple2<String, String>, FlightInfo> airportsDelay =
                flightLines.mapToPair(line -> {
                    String[] fields = line.split(",");
                    return new Tuple2<>(
                            new Tuple2<>(fields[ORIGIN_AIRPORT_ID_FILED], fields[DEST_AIRPORT_ID_FILED]),
                            new FlightInfo(Float.parseFloat()))
                });

    }
}
