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

import java.util.Map;

public class DelayStatApp {
    private static final String[] flightHeader = {
            "YEAR","QUARTER","MONTH", "DAY_OF_MONTH","DAY_OF_WEEK","FL_DATE","UNIQUE_CARRIER",
            "AIRLINE_ID","CARRIER","TAIL_NUM","FL_NUM","ORIGIN_AIRPORT_ID","ORIGIN_AIRPORT_SEQ_ID",
            "ORIGIN_CITY_MARKET_ID","DEST_AIRPORT_ID","WHEELS_ON","ARR_TIME","ARR_DELAY",
            "ARR_DELAY_NEW","CANCELLED","CANCELLATION_CODE","AIR_TIME","DISTANCE"
    };
    private static final String[] airportHeader = {"Code", "Description"};

    private static final String ORIGIN_AIRPORT_ID_FIELD = "ORIGIN_AIRPORT_ID";
    private static final String DEST_AIRPORT_ID_FIELD = "DEST_AIRPORT_ID";
    private static final String DELAY_FIELD = "ARR_DELAY_NEW";
    private static final String CANCELED_FIELD = "CANCELLED";
    private static final String CODE_FIELD = "Code";
    private static final String DESCRIPTION_FIELD = "Description";


    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("DelayStatApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightLines = sc.textFile("flights_data.csv");
        JavaPairRDD<Tuple2<String, String>, BadFlightsStat> airportsBadFlightsStat = flightLines
                .mapToPair(line -> {
                    CSVParser parser = CSVParser.parse(line.toString(), CSVFormat.RFC4180.withHeader(flightHeader));
                    CSVRecord record = parser.getRecords().get(0);

                    return new Tuple2<>(
                            new Tuple2<>(record.get(ORIGIN_AIRPORT_ID_FIELD), record.get(DEST_AIRPORT_ID_FIELD)),
                            new BadFlightsStat(record.get(DELAY_FIELD), record.get(CANCELED_FIELD)));
                });
        airportsBadFlightsStat.reduceByKey(BadFlightsStat::add);

        JavaRDD<String>airportLines = sc.textFile("airports_data.csv");
        JavaPairRDD<String, String> airportNames = airportLines
                .mapToPair(line -> {
                    CSVParser parser = CSVParser.parse(line.toString(), CSVFormat.RFC4180.withHeader(airportHeader));
                    CSVRecord record = parser.getRecords().get(0);

                    return new Tuple2<>(record.get(CODE_FIELD), record.get(DESCRIPTION_FIELD));
                });
        Map<String,String> airportNamesMap = airportNames.collectAsMap();
    }
}
