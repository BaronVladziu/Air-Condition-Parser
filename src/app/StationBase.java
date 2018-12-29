package app;

import calculable.*;
import gios.*;
import parser.GiosParser;

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
            System.out.println("Sensors: " + this.sensors.size());
            this.cacheUpdater.updateDataCache(sensors.values());
            this.giosParser.parseData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Index getIndex(String stationName) {
        return this.stations.get(this.stationName2ID.get(stationName)).index;
    }

    public void printIndex(Iterable<String> stationNames) {
        for (String stationName : stationNames) {
            System.out.println(this.getIndex(stationName));
        }
    }

    public List<Data> getValues(Date date, String stationName, Parameter param) {
        return this.getValues(date, this.stations.get(this.stationName2ID.get(stationName.toLowerCase())), param);
    }

    public List<Data> getValues(Date date, Station station, Parameter param) {
        List<Data> values = new LinkedList<>();
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    if (data.date.compareTo(date) < 0) {
                        values.add(data);
                    }
                }
            }
        }
        return values;
    }

    public List<Data> getValues(Date startDate, Date endDate, Station station, Parameter param) {
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

    public void printValues(Parameter param, Date date, List<String> stations) {
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                for (Data data : this.getValues(date, station, param)) {
                    System.out.println(data);
                }
            }
        } else {
            for (String stationName : stations) {
                for (Data data : this.getValues(date, stationName, param)) {
                    System.out.println(data);
                }
            }
        }
    }

    public NamedValue getLowestParam(Date date, Collection<Station> stations) throws ValueNotFoundException {
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

    public void printLowestParam(Date date, List<String> stations) {
        try {
            if (stations.size() == 0) {
                System.out.println(this.getLowestParam(date, this.stations.values()));
            } else {
                List<Station> stationList = new LinkedList<>();
                for (String stationName : stations) {
                    stationList.add(this.stations.get(this.stationName2ID.get(stationName.toLowerCase())));
                }
                System.out.println(this.getLowestParam(date, stationList));
            }
        } catch (ValueNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public NamedValue getHighestParam(Date date, Collection<Station> stations) throws ValueNotFoundException {
        //Get minimum of every parameter
        float[] values = new float[Parameter.size];
        for (int i = 0; i < Parameter.size; i++) {
            values[i] = Float.NEGATIVE_INFINITY;
            for (Station station : stations) {
                for (Data data : this.getValues(date, station.name, Parameter.values()[i])) {
                    values[i] = Math.min(values[i], data.value);
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

    public void printHighestParam(Date date, List<String> stations) {
        try {
            if (stations.size() == 0) {
                System.out.println(this.getHighestParam(date, this.stations.values()));
            } else {
                List<Station> stationList = new LinkedList<>();
                for (String stationName : stations) {
                    stationList.add(this.stations.get(this.stationName2ID.get(stationName.toLowerCase())));
                }
                System.out.println(this.getHighestParam(date, stationList));
            }
        } catch (ValueNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<NamedValue> getNamedValues(Station station, Date date, Parameter param) {
        List<NamedValue> values = new LinkedList<>();
        for (Sensor sensor : station.sensors) {
            if (sensor.paramCode == param) {
                for (Data data : sensor.data) { //data is sorted by date
                    if (data.date.compareTo(date) < 0) {
                        values.add(new NamedValue(Integer.toString(sensor.id), data.value));
                    }
                }
            }
        }
        return values;
    }

    public List<NamedValue> getNamedValues(String stationName, Date date, Parameter param) {
        return this.getNamedValues(this.stations.get(this.stationName2ID.get(stationName)), date, param);
    }

    public List<NamedValue> getNLowestValues(String stationName, Date date, Parameter param, int N) {
        List<NamedValue> values = this.getNamedValues(stationName, date, param);
        Collections.sort(values,Collections.reverseOrder());
        return values.subList(0, N-1);
    }

    public void printLowestNValues(Parameter param, Date date, int N, List<String> stations) {
        List<NamedValue> values = new LinkedList<>();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                values.addAll(this.getNamedValues(station, date, param));
            }
        } else {
            for (String stationName : stations) {
                values.addAll(this.getNamedValues(this.stations.get(this.stationName2ID.get(stationName)), date, param));
            }
        }
        Collections.sort(values,Collections.reverseOrder());
        for (NamedValue value : values.subList(0, N - 1)) {
            System.out.println(value);
        }
    }

    public List<NamedValue> getNHighestValues(String stationName, Date date, String param, int N) {
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
        return values.subList(0, N-1);
    }

    public void printHighestNValues(Parameter param, Date date, int N, List<String> stations) {
        List<NamedValue> values = new LinkedList<>();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                values.addAll(this.getNamedValues(station, date, param));
            }
        } else {
            for (String stationName : stations) {
                values.addAll(this.getNamedValues(this.stations.get(this.stationName2ID.get(stationName)), date, param));
            }
        }
        Collections.sort(values);
        for (NamedValue value : values.subList(0, N - 1)) {
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

    private Addable<Float> updateFloatAddable(Addable<Float> addable, Station station, Parameter param, Date startDate, Date endDate) throws ValueNotFoundException {
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

    private Addable<NamedDatedValue> updateNamedDatedValueAddable(Addable<NamedDatedValue> addable, Station station, Parameter param, Date startDate, Date endDate) throws ValueNotFoundException {
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
        Mean mean = new Mean();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateFloatAddable(mean, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateFloatAddable(mean, this.stations.get(this.stationName2ID.get(stationName)), param);
            }
        }
        System.out.println(mean.calculate());
    }

    public void printMean(Parameter param, Date startDate, Date endDate, List<String> stations) {
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
                    this.updateFloatAddable(mean, this.stations.get(this.stationName2ID.get(stationName)), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(mean.calculate());
    }

    public void printVariance(Parameter param, List<String> stations) {
        Variance variance = new Variance();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateFloatAddable(variance, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateFloatAddable(variance, this.stations.get(this.stationName2ID.get(stationName)), param);
            }
        }
        System.out.println(variance.calculate());
    }

    public void printVariance(Parameter param, Date startDate, Date endDate, List<String> stations) {
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
                    this.updateFloatAddable(variance, this.stations.get(this.stationName2ID.get(stationName)), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(variance.calculate());
    }

    public void printRange(Parameter param, List<String> stations) {
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                this.updateNamedDatedValueAddable(range, station, param);
            }
        } else {
            for (String stationName : stations) {
                this.updateNamedDatedValueAddable(range, this.stations.get(this.stationName2ID.get(stationName)), param);
            }
        }
        System.out.println(range.calculateUp());
        System.out.println(range.calculateDown());
    }

    public void printRange(Parameter param, Date startDate, Date endDate, List<String> stations) {
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
                    this.updateNamedDatedValueAddable(range, this.stations.get(this.stationName2ID.get(stationName)), param, startDate, endDate);
                } catch (ValueNotFoundException ex) {
                    //Ignore missing values
                }
            }
        }
        System.out.println(range.calculateUp());
        System.out.println(range.calculateDown());
    }

    public void printFigure(Parameter param, List<String> stations) {
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Sensor sensor : this.sensors.values()) {
                for (Data data : sensor.data) {
                    System.out.println(data);
                }
            }
        } else {
            for (String stationName : stations) {
                for (Sensor sensor : this.stations.get(this.stationName2ID.get(stationName)).sensors) {
                    for (Data data : sensor.data) {
                        System.out.println(data);
                    }
                }
            }
        }
    }

    public void printFigure(Parameter param, Date startDate, Date endDate, List<String> stations) {
        NamedDatedRange range = new NamedDatedRange();
        if (stations.size() == 0) {
            for (Station station : this.stations.values()) {
                for (Data data : this.getValues(startDate, endDate, station, param)) {
                    System.out.println(data);
                }
            }
        } else {
            for (String stationName : stations) {
                for (Data data : this.getValues(startDate, endDate, this.stations.get(this.stationName2ID.get(stationName)), param)) {
                    System.out.println(data);
                }
            }
        }
    }

}
