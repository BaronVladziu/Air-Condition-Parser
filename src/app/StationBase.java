package app;

import calculable.*;
import gios.*;
import parser.GiosParser;

import java.time.LocalDateTime;
import java.util.*;

public class StationBase {

    private final CacheUpdater cacheUpdater = new CacheUpdater();
    private final GiosParser giosParser = new GiosParser();

    private Map<Integer, Station> stations;
    private Map<Integer, City> cities;
    private Map<Integer, Sensor> sensors;
    private Map<String, Integer> stationName2ID;

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
            this.stationName2ID = this.giosParser.getStationNames2IDs();
            System.out.println("Sensors: " + this.sensors.size());
            this.cacheUpdater.updateDataCache(sensors.values());
            this.giosParser.parseData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Index getIndex(String stationName) {
        return this.getStation(stationName.toLowerCase()).index;
    }

    public void printIndex(Collection<String> stationNames) {
        if (stationNames.size() == 0) {
            for (Station station : this.stations.values()) {
                System.out.println("Index for " + station.name.toUpperCase() + ":");
                System.out.println(station.index + "\n");
            }
        } else {
            for (String stationName : stationNames) {
                System.out.println("Index for " + stationName.toUpperCase() + ":");
                System.out.println(this.getIndex(stationName) + "\n");
            }
        }
    }

    public List<Data> getValues(LocalDateTime date, String stationName, Parameter param) {
        return this.getValues(date, this.getStation(stationName.toLowerCase()), param);
    }

    public List<Data> getValues(LocalDateTime date, Station station, Parameter param) {
        List<Data> values = new LinkedList<>();
        for (Sensor sensor : station.sensors) {
            try {
                values.add(this.getValue(date, sensor, param));
            } catch (ValueNotFoundException ex) {
                //ignore non-existing values
            }
        }
        return values;
    }

    public Data getValue(LocalDateTime date, Sensor sensor, Parameter param) throws ValueNotFoundException {
        if (sensor.paramCode == param) {
            for (Data data : sensor.data) { //data is sorted by date
                if (data.date.compareTo(date) <= 0) {
                    return data;
                }
            }
        }
        throw new ValueNotFoundException("No data found");
    }

    public List<Data> getValues(LocalDateTime startDate, LocalDateTime endDate, Station station, Parameter param) {
        List<Data> values = new LinkedList<>();
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    if (data.date.compareTo(startDate) < 0) {
                        return values;
                    } else if (data.date.compareTo(endDate) < 0) {
                        values.add(data);
                    }
                }
            }
        }
        return values;
    }

    public void printValues(Parameter param, LocalDateTime date, List<String> stations) {
        System.out.println("--- Values of " + param + " ---");
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                List<Data> dataList = this.getValues(date, station, param);
                if (dataList.size() > 0) {
                    System.out.println(station.name + ":");
                    for (Data data : dataList) {
                        System.out.println(data);
                    }
                }
            }
        } else {
            for (String stationName : stations) {
                Station station = this.getStation(stationName.toLowerCase());
                List<Data> dataList = this.getValues(date, station, param);
                if (dataList.size() > 0) {
                    System.out.println(station.name + ":");
                    for (Data data : dataList) {
                        System.out.println(data);
                    }
                }
            }
        }

    }

    public NamedValue getLowestParam(LocalDateTime date, Collection<Station> stations) throws ValueNotFoundException {
        //Get minimum of every parameter
        float[] values = new float[Parameter.size];
        for (int i = 0; i < Parameter.size; i++) {
            values[i] = Float.POSITIVE_INFINITY;
            for (Station station : stations) {
                for (Data data : this.getValues(date, station.name, Parameter.values()[i])) {
                    values[i] = Math.min(values[i], data.value);
                }
            }
        }
        //get minimum of values
        int minID = 0;
        for (int i = 1; i < Parameter.size; i++) {
            if (values[i] < values[minID]) {
                minID = i;
            }
        }
        if (Float.isFinite(values[minID])) {
            return new NamedValue(Parameter.values()[minID].toString(), values[minID]);
        }
        throw new ValueNotFoundException("No data found");
    }

    public void printLowestParam(LocalDateTime date, List<String> stations) {
        System.out.println("--- Lowest parameter ---");
        try {
            if (stations.size() == 0) {
                System.out.println(this.getLowestParam(date, this.stations.values()));
            } else {
                List<Station> stationList = new LinkedList<>();
                for (String stationName : stations) {
                    stationList.add(this.getStation(stationName.toLowerCase()));
                }
                System.out.println(this.getLowestParam(date, stationList));
            }
        } catch (ValueNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public NamedValue getHighestParam(LocalDateTime date, Collection<Station> stations) throws ValueNotFoundException {
        //Get minimum of every parameter
        float[] values = new float[Parameter.size];
        for (int i = 0; i < Parameter.size; i++) {
            values[i] = Float.NEGATIVE_INFINITY;
            for (Station station : stations) {
                for (Data data : this.getValues(date, station.name, Parameter.values()[i])) {
                    values[i] = Math.max(values[i], data.value);
                }
            }
        }
        //get maximum of values
        int maxID = 0;
        for (int i = 1; i < Parameter.size; i++) {
            if (values[i] > values[maxID]) {
                maxID = i;
            }
        }
        if (Float.isFinite(values[maxID])) {
            return new NamedValue(Parameter.values()[maxID].toString(), values[maxID]);
        }
        throw new ValueNotFoundException("No data found");
    }

    public void printHighestParam(LocalDateTime date, List<String> stations) {
        System.out.println("--- Highest parameter ---");
        try {
            if (stations.size() == 0) {
                System.out.println(this.getHighestParam(date, this.stations.values()));
            } else {
                List<Station> stationList = new LinkedList<>();
                for (String stationName : stations) {
                    stationList.add(this.getStation(stationName.toLowerCase()));
                }
                System.out.println(this.getHighestParam(date, stationList));
            }
        } catch (ValueNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<NamedValue> getNamedValues(Station station, LocalDateTime date, Parameter param) {
        List<NamedValue> values = new LinkedList<>();
        for (Sensor sensor : station.sensors) {
            try {
                values.add(new NamedValue(station.name, this.getValue(date, sensor, param).value));
            } catch (ValueNotFoundException ex) {
                //ignore non-existing values
            }
        }
        return values;
    }

    public List<NamedValue> getNamedValues(String stationName, LocalDateTime date, Parameter param) {
        return this.getNamedValues(this.getStation(stationName.toLowerCase()), date, param);
    }

    public List<NamedValue> getNLowestValues(String stationName, LocalDateTime date, Parameter param, int N) {
        List<NamedValue> values = this.getNamedValues(stationName, date, param);
        Collections.sort(values,Collections.reverseOrder());
        return values.subList(0, N);
    }

    public void printLowestNValues(Parameter param, LocalDateTime date, int N, List<String> stations) {
        System.out.println("--- Lowest " + N + " values of " + param + " ---");
        List<NamedValue> values = new LinkedList<>();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                values.addAll(this.getNamedValues(station, date, param));
            }
        } else {
            for (String stationName : stations) {
                values.addAll(this.getNamedValues(this.getStation(stationName.toLowerCase()), date, param));
            }
        }
        Collections.sort(values,Collections.reverseOrder());
        for (NamedValue value : values.subList(0, N)) {
            System.out.println(value);
        }
    }

    public List<NamedValue> getNHighestValues(String stationName, LocalDateTime date, String param, int N) {
        List<NamedValue> values = new LinkedList<>();
        for (Sensor sensor : this.stations.get(this.stationName2ID.get(stationName.toLowerCase())).sensors) {
            if (sensor.paramCode == Parameter.valueOf(param.toUpperCase())) {
                for (Data data : sensor.data) { //data is sorted by date
                    if (data.date.compareTo(date) < 0) {
                        values.add(new NamedValue(Integer.toString(sensor.id), data.value));
                    }
                }
            }
        }
        Collections.sort(values);
        return values.subList(0, N);
    }

    public void printHighestNValues(Parameter param, LocalDateTime date, int N, List<String> stations) {
        System.out.println("--- Highest " + N + " values of " + param + " ---");
        List<NamedValue> values = new LinkedList<>();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                values.addAll(this.getNamedValues(station, date, param));
            }
        } else {
            for (String stationName : stations) {
                values.addAll(this.getNamedValues(this.getStation(stationName.toLowerCase()), date, param));
            }
        }
        Collections.sort(values);
        for (NamedValue value : values.subList(0, N)) {
            System.out.println(value);
        }
    }

    private Addable<Float> updateFloatAddable(Addable<Float> addable, Station station, Parameter param) {
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) {
                    addable.add(data.value);
                }
            }
        }
        return addable;
    }

    private Addable<Float> updateFloatAddable(Addable<Float> addable, Station station, Parameter param, LocalDateTime startDate, LocalDateTime endDate) throws ValueNotFoundException {
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    addable.add(data.value);
                    if (data.date.compareTo(startDate) < 0) {
                        return addable;
                    } else if (data.date.compareTo(endDate) < 0) {
                        addable.add(data.value);
                    }
                }
            }
        }
        throw new ValueNotFoundException("No data found");
    }

    private Addable<NamedDatedValue> updateNamedDatedValueAddable(Addable<NamedDatedValue> addable, Station station, Parameter param) {
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    addable.add(new NamedDatedValue(station.name, data.date, data.value));
                }
            }
        }
        return addable;
    }

    private Addable<NamedDatedValue> updateNamedDatedValueAddable(Addable<NamedDatedValue> addable, Station station, Parameter param, LocalDateTime startDate, LocalDateTime endDate) throws ValueNotFoundException {
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    addable.add(new NamedDatedValue(station.name, data.date, data.value));
                    if (data.date.compareTo(startDate) < 0) {
                        return addable;
                    } else if (data.date.compareTo(endDate) < 0) {
                        addable.add(new NamedDatedValue(station.name, data.date, data.value));
                    }
                }
            }
        }
        throw new ValueNotFoundException("No data found");
    }

    public void printMean(Parameter param, List<String> stations) {
        System.out.println("--- Mean of " + param + " ---");
        Mean mean = new Mean();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateFloatAddable(mean, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateFloatAddable(mean, this.getStation(stationName.toLowerCase()), param);
            }
        }
        System.out.println(mean.calculate());
    }

    public void printMean(Parameter param, LocalDateTime startDate, LocalDateTime endDate, List<String> stations) {
        System.out.println("--- Mean of " + param + " ---");
        Mean mean = new Mean();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                try {
                    this.updateFloatAddable(mean, station, param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        } else {
            for (String stationName : stations) {
                try {
                    this.updateFloatAddable(mean, this.getStation(stationName.toLowerCase()), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(mean.calculate());
    }

    public void printVariance(Parameter param, List<String> stations) {
        System.out.println("--- Variance of " + param + " ---");
        Variance variance = new Variance();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateFloatAddable(variance, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateFloatAddable(variance, this.getStation(stationName.toLowerCase()), param);
            }
        }
        System.out.println(variance.calculate());
    }

    public void printVariance(Parameter param, LocalDateTime startDate, LocalDateTime endDate, List<String> stations) {
        System.out.println("--- Variance of " + param + " ---");
        Variance variance = new Variance();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                try {
                    this.updateFloatAddable(variance, station, param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        } else {
            for (String stationName : stations) {
                try {
                    this.updateFloatAddable(variance, this.getStation(stationName.toLowerCase()), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(variance.calculate());
    }

    public void printRange(Parameter param, List<String> stations) {
        System.out.println("--- Range of " + param + " ---");
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateNamedDatedValueAddable(range, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateNamedDatedValueAddable(range, this.getStation(stationName.toLowerCase()), param);
            }
        }
        System.out.println(range.calculateUp());
        System.out.println(range.calculateDown());
    }

    public void printRange(Parameter param, LocalDateTime startDate, LocalDateTime endDate, List<String> stations) {
        System.out.println("--- Range of " + param + " ---");
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                try {
                    this.updateNamedDatedValueAddable(range, station, param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        } else {
            for (String stationName : stations) {
                try {
                    this.updateNamedDatedValueAddable(range, this.getStation(stationName.toLowerCase()), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(range.calculateUp());
        System.out.println(range.calculateDown());
    }

    public void printFigure(Parameter param, List<String> stations) {
        System.out.println("--- Figure of " + param + " ---");
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                System.out.println(station.name + ":");
                for (Sensor sensor : station.sensors) {
                    if (sensor.paramCode == param) {
                        for (Data data : sensor.data) {
                            for (int i = 1; i < data.value / 10; i++) {
                                System.out.print('#');
                            }
                            System.out.println(" " + data);
                        }
                    }
                }
            }
        } else {
            for (String stationName : stations) {
                System.out.println(stationName + ":");
                for (Sensor sensor : this.getStation(stationName.toLowerCase()).sensors) {
                    if (sensor.paramCode == param) {
                        for (Data data : sensor.data) {
                            for (int i = 1; i < data.value / 10; i++) {
                                System.out.print('#');
                            }
                            System.out.println(" " + data);
                        }
                    }
                }
            }
        }
    }

    public void printFigure(Parameter param, LocalDateTime startDate, LocalDateTime endDate, List<String> stations) {
        System.out.println("--- Figure of " + param + " ---");
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                System.out.println(station.name + ":");
                for (Data data : this.getValues(startDate, endDate, station, param)) {
                    for (int i = 1; i < data.value / 10; i++) {
                        System.out.print('#');
                    }
                    System.out.println(" " + data);
                }
            }
        } else {
            for (String stationName : stations) {
                System.out.println(stationName + ":");
                for (Data data : this.getValues(startDate, endDate, this.getStation(stationName.toLowerCase()), param)) {
                    for (int i = 1; i < data.value / 10; i++) {
                        System.out.print('#');
                    }
                    System.out.println(" " + data);
                }
            }
        }
    }

    private Station getStation(String stationName) {
        Station result = this.stations.get(this.stationName2ID.get(stationName));
        if (result != null) return result;
        throw new RuntimeException("ERROR: Unknown stationName!");
    }

}
