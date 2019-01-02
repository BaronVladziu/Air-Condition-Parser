package parser;

import gios.*;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GiosParser {

    private final CityFactory cityFactory = new CityFactory();
    private final StationFactory stationFactory = new StationFactory();
    private final SensorFactory sensorFactory = new SensorFactory();
    private final IndexFactory indexFactory = new IndexFactory();

    private Map<Integer, City> cities = new HashMap<>();
    private Map<Integer, Station> stations = new HashMap<>();
    private Map<Integer, Sensor> sensors = new HashMap<>();
    private Map<String, Integer> stationName2ID = new HashMap<>();


    public void parseStations(String dirName) throws IOException {
        System.out.println("--- Parsing stations ---");
        final String file = new String(Files.readAllBytes(Paths.get(dirName + "/station-findAll.txt")));
        this.parseStations(file, dirName);
    }

    private void parseStations(String file, String dirName) throws IOException {
        final JsonParser parser = Json.createParser(new StringReader(file));
        String prevKey = null;
        String key = null;
        String value = null;
        while (parser.hasNext()) {
            final JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    prevKey = key;
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString().toLowerCase();
                    switch (key) {
                        case "stationName":
                            this.stationFactory.name = value;
                            break;
                        case "gegrLat":
                            this.stationFactory.gegrLat = Float.parseFloat(value);
                            break;
                        case "gegrLon":
                            this.stationFactory.gegrLon = Float.parseFloat(value);
                            break;
                        case "name":
                            this.cityFactory.cityName = value;
                            break;
                        case "communeName":
                            this.cityFactory.communeName = value;
                            break;
                        case "districtName":
                            this.cityFactory.districtName = value;
                            break;
                        case "provinceName":
                            this.cityFactory.provinceName = value;
                            City city = this.cityFactory.createInstance();
                            this.cities.put(city.id, city);
                            break;
                        case "addressStreet":
                            this.stationFactory.addressStreet = value;
                            this.stationFactory.index = this.parseIndex(dirName, this.stationFactory.id);
                            Station station = this.stationFactory.createInstance();
                            this.stations.put(station.id, station);
                            this.stationName2ID.put(station.name, station.id);
                            break;
                        default:
                            throw new RuntimeException("Parsing error: Unknown key: " + key);
                    }
                    break;
                case VALUE_NUMBER:
                    int intValue = parser.getInt();
                    switch (key) {
                        case "id":
                            if (prevKey != null && prevKey.equals("city")) {
                                this.cityFactory.id = intValue;
                                this.stationFactory.cityID = intValue;
                            } else {
                                this.stationFactory.id = intValue;
                            }
                            break;
                        default:
                            throw new RuntimeException("Parsing error: Unknown key: " + key);
                    }
                    break;
            }
        }
        parser.close();
    }

    public void parseSensors(String dirName) throws IOException {
        System.out.println("--- Parsing sensors ---");
        for (Station station : this.stations.values()) {
            final String file = new String(Files.readAllBytes(Paths.get(dirName + "/sensors-" + station.id + ".txt")));
            this.parseSensor(file);
        }
    }

    private void parseSensor(String file) {
        final JsonParser parser = Json.createParser(new StringReader(file));
        String key = null;
        String value = null;
        while (parser.hasNext()) {
            final JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString().toLowerCase();
                    switch (key) {
                        case "paramName":
                            this.sensorFactory.paramName = value;
                            break;
                        case "paramFormula":
                            this.sensorFactory.paramFormula = value;
                            break;
                        case "paramCode":
                            if (value.toUpperCase().equals("PM2.5")) value = "pm25";
                            this.sensorFactory.paramCode = value;
                            break;
                        default:
                            throw new RuntimeException("Parsing error: Unknown key: " + key);
                    }
                    break;
                case VALUE_NUMBER:
                    int intValue = parser.getInt();
                    switch (key) {
                        case "id":
                            this.sensorFactory.id = intValue;
                            break;
                        case "stationId":
                            this.sensorFactory.stationID = intValue;
                            break;
                        case "idParam":
                            this.sensorFactory.paramID = intValue;
                            Sensor sensor = this.sensorFactory.createInstance();
                            this.sensors.put(sensor.id, sensor);
                            this.stations.get(sensor.stationID).addSensor(sensor);
                            break;
                        default:
                            throw new RuntimeException("Parsing error: Unknown key: " + key);
                    }
                    break;
            }
        }
        parser.close();
    }

    public void parseData(String dirName) throws IOException {
        System.out.println("--- Parsing data ---");
        LocalDateTime date = null;
        long counter = 0;
        for (Sensor sensor : this.sensors.values()) {
            final String file = new String(Files.readAllBytes(Paths.get(dirName + "/data-" + sensor.id + ".txt")));
            counter += this.parseData(file, sensor);
        }
        System.out.println("Data: " + counter);
        System.out.println("PARSING DONE\n");
    }

    private long parseData(String file, Sensor sensor) {
        long counter = 0;
        final JsonParser parser = Json.createParser(new StringReader(file));
        LocalDateTime date = null;
        String key = null;
        String[] value = null;
        while (parser.hasNext()) {
            final JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    String temp2 = parser.getString().toLowerCase();
                    if (temp2.split(" ").length > 1) {
                        date = parseDate(temp2);
                    }
                    break;
                case VALUE_NUMBER:
                    int intValue = parser.getInt();
                    this.sensors.get(sensor.id).addData(new Data(date, intValue));
                    counter++;
                    break;
            }
        }
        parser.close();
        return counter;
    }

    private Index parseIndex(String dirName, int stationID) throws IOException {
        final String file = new String(Files.readAllBytes(Paths.get(dirName + "/index-" + stationID + ".txt")));
        return this.parseIndex(file);
    }

    private Index parseIndex(String file) {
        final JsonParser parser = Json.createParser(new StringReader(file));
        String key = null;
        String value = null;
        LocalDateTime calcDate = null;
        String indexLevel = null;
        while (parser.hasNext()) {
            final JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString().toLowerCase();
                    if (!value.equals("pyl")) {
                        switch (key) {
                            case "stCalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "so2CalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "no2CalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "coCalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "pm10CalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "pm25CalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "o3CalcDate":
                                calcDate = parseDate(value);
                                break;
                            case "c6h6CalcDate":
                                calcDate = parseDate(value);
                                break;

                            case "indexLevelName":
                                indexLevel = value;
                                break;

                            case "stSourceDataDate":
                                this.indexFactory.st = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "so2SourceDataDate":
                                this.indexFactory.so2 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "no2SourceDataDate":
                                this.indexFactory.no2 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "coSourceDataDate":
                                this.indexFactory.co = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "pm10SourceDataDate":
                                this.indexFactory.pm10 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "pm25SourceDataDate":
                                this.indexFactory.pm25 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "o3SourceDataDate":
                                this.indexFactory.o3 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;
                            case "c6h6SourceDataDate":
                                this.indexFactory.c6h6 = new IndexValue(indexLevel, calcDate, parseDate(value));
                                break;

                            default:
                                throw new RuntimeException("Parsing error: Unknown index key: " + key);
                        }
                    }
                    break;
            }
        }
        parser.close();
        return this.indexFactory.createInstance();
    }

    public final Map<Integer, City> getCities() {
        return this.cities;
    }

    public final Map<Integer, Station> getStations() {
        return this.stations;
    }

    public final Map<Integer, Sensor> getSensors() {
        return this.sensors;
    }

    public final Map<String, Integer> getStationNames2IDs() {
        return this.stationName2ID;
    }

    public static LocalDateTime parseDate(String string) {
        String[] temp = string.split(" ");
        String[] value = Stream.of(temp[0].split("-"), temp[1].split(":")).flatMap(Stream::of)
                .toArray(String[]::new);
        return LocalDateTime.of(Integer.valueOf(value[0]), Integer.valueOf(value[1]), Integer.valueOf(value[2]),
                Integer.valueOf(value[3]), Integer.valueOf(value[4]), Integer.valueOf(value[5]));
    }

}
