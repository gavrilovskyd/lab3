package ru.labs.flights;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import scala.Tuple4;

public class DelayStatApp {
    private static final double EPS = 1e-6;
    private static final String[] flightHeader = {
            "YEAR","QUARTER","MONTH", "DAY_OF_MONTH","DAY_OF_WEEK","FL_DATE","UNIQUE_CARRIER",
            "AIRLINE_ID","CARRIER","TAIL_NUM","FL_NUM","ORIGIN_AIRPORT_ID","ORIGIN_AIRPORT_SEQ_ID",
            "ORIGIN_CITY_MARKET_ID","DEST_AIRPORT_ID","WHEELS_ON","ARR_TIME","ARR_DELAY",
            "ARR_DELAY_NEW","CANCELLED","CANCELLATION_CODE","AIR_TIME","DISTANCE"
    };

    private static final String ORIGIN_AIRPORT_ID_FIELD = "ORIGIN_AIRPORT_ID";
    private static final String DEST_AIRPORT_ID_FIELD = "DEST_AIRPORT_ID";
    private static final String DELAY_FIELD = "ARR_DELAY_NEW";
    private static final String CANCELED_FIELD = "CANCELLED";



    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightLines = sc.textFile("flights_data.csv");
        JavaPairRDD<Tuple2<String, String>, Float> airportsDelay =
                flightLines.map(line -> {
                    CSVParser parser = CSVParser.parse(line.toString(), CSVFormat.RFC4180.withHeader(flightHeader));
                    CSVRecord record = parser.getRecords().get(0);

                    return new Tuple4<>(
                            record.get(ORIGIN_AIRPORT_ID_FIELD), record.get(DEST_AIRPORT_ID_FIELD),
                            record.get(DELAY_FIELD), record.get(CANCELED_FIELD));
                })
                        .filter(parsedRecord -> Float.parseFloat(parsedRecord._4())< EPS
                        && !parsedRecord._3().isEmpty() && Float.parseFloat(parsedRecord._3()) > EPS)
                        .mapToPair();

    }
}
