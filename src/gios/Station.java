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

    public boolean equals(Object obj) {
        Station that = (Station) obj;
        return (this.id == that.id &&
                this.name.equals(that.name) &&
                this.gegrLat == that.gegrLat &&
                this.gegrLon == that.gegrLon &&
                this.cityID == that.cityID &&
                this.addressStreet.equals(that.addressStreet) &&
                this.index.equals(that.index));
    }

    public String toString() {
        return "Station id: " + id + "\n" +
                "Station name: " + name + "\n" +
                "gegrLat: " + gegrLat + "\n" +
                "gegrLon: " + gegrLon + "\n" +
                "cityID: " + cityID + "\n" +
                "addressStreet: " + addressStreet + "\n" +
                "index: " + index;
    }

}
