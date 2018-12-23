package app;

import gios.City;
import gios.Sensor;
import gios.Station;
import parser.GiosParser;

import java.util.Map;

public class StationBase {

    private final CacheUpdater cacheUpdater = new CacheUpdater();
    private final GiosParser giosParser = new GiosParser();

    private Map<String, Station> stations;
    private Map<Integer, City> cities;
    private Map<Integer, Sensor> sensors;

    public StationBase () {
        try {
            this.cacheUpdater.updateStationCache();
            this.giosParser.parseStations();
            this.stations = giosParser.getStations();
            this.cities = giosParser.getCities();
            System.out.println("Cities: " + this.cities.size());
            System.out.println("Stations: " + this.stations.size());
            this.cacheUpdater.updateSensorCache(stations.values());
            this.cacheUpdater.updateIndexCache(stations.values());
            this.giosParser.parseSensors();
            this.sensors = this.giosParser.getSensors();
            System.out.println("Sensors: " + this.sensors.size());
            this.cacheUpdater.updateDataCache(sensors.values());
            this.giosParser.parseData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
