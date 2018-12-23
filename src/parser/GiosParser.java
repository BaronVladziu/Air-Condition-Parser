package parser;

import gios.*;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GiosParser {

    private final CityFactory cityFactory = new CityFactory();
    private final StationFactory stationFactory = new StationFactory();
    private final SensorFactory sensorFactory = new SensorFactory();

    private Map<Integer, City> cities = new HashMap<>();
    private Map<String, Station> stations = new HashMap<>();
    private Map<Integer, Sensor> sensors = new HashMap<>();


    public void parseStations() throws IOException {
        System.out.println("--- Parsing stations ---");
        final String result = new String(Files.readAllBytes(Paths.get("cache/station-findAll.txt")));
        final JsonParser parser = Json.createParser(new StringReader(result));
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
                    value = parser.getString();
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
                            Station station = this.stationFactory.createInstance();
                            this.stations.put(station.name, station);
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

    public void parseSensors() throws IOException {
        System.out.println("--- Parsing sensors ---");
        for (Station station : this.stations.values()) {
            final String result = new String(Files.readAllBytes(Paths.get("cache/sensors-" + station.id + ".txt")));
            final JsonParser parser = Json.createParser(new StringReader(result));
            String key = null;
            String value = null;
            while (parser.hasNext()) {
                final JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        key = parser.getString();
                        break;
                    case VALUE_STRING:
                        value = parser.getString();
                        switch (key) {
                            case "paramName":
                                this.sensorFactory.paramName = value;
                                break;
                            case "paramFormula":
                                this.sensorFactory.paramFormula = value;
                                break;
                            case "paramCode":
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
                                break;
                            default:
                                throw new RuntimeException("Parsing error: Unknown key: " + key);
                        }
                        break;
                }
            }
            parser.close();
        }
    }

    public void parseData() throws IOException {
        System.out.println("--- Parsing data ---");
        for (Sensor sensor : this.sensors.values()) {
            final String result = new String(Files.readAllBytes(Paths.get("cache/data-" + sensor.id + ".txt")));
            final JsonParser parser = Json.createParser(new StringReader(result));
            String key = null;
            String value = null;
            while (parser.hasNext()) {
                final JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        key = parser.getString();
                        break;
                    case VALUE_STRING:
                        value = parser.getString();
                        System.out.println(key + " = " + value);
                        break;
                    case VALUE_NUMBER:
                        int intValue = parser.getInt();
                        System.out.println(key + " = " + intValue);
                        break;
                }
            }
            parser.close();
        }
    }

    public final Map<Integer, City> getCities() {
        return this.cities;
    }

    public final Map<String, Station> getStations() {
        return this.stations;
    }

    public final Map<Integer, Sensor> getSensors() {
        return this.sensors;
    }


}
