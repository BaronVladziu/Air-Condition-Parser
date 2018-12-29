package gios;

import java.util.LinkedList;
import java.util.List;

public class Station {

    public final int id;
    public final String name;
    public final float gegrLat;
    public final float gegrLon;
    public final int cityID;
    public final String addressStreet;
    public final Index index;

    public final List<Sensor> sensors = new LinkedList<>();


    public Station(int id, String name, float gegrLat, float gegrLon, int cityID, String addressStreet, Index index) {
        this.id = id;
        this.name = name;
        this.gegrLat = gegrLat;
        this.gegrLon = gegrLon;
        this.cityID = cityID;
        this.addressStreet = addressStreet;
        this.index = index;
    }

    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

}
