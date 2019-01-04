package gios;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing station.
 * @author Bartłomiej Kuśmirek
 */
public class Station {

    /**
     * station id
     */
    public final int id;
    /**
     * station name
     */
    public final String name;
    /**
     * ?
     */
    public final float gegrLat;
    /**
     * ?
     */
    public final float gegrLon;
    /**
     * id of the city, the station's in
     */
    public final int cityID;
    /**
     * station address
     */
    public final String addressStreet;
    /**
     * air quality index
     */
    public Index index;
    /**
     * station's sensors
     */
    public final List<Sensor> sensors = new LinkedList<>();

    /**
     * Constructor
     * @param id station id
     * @param name station name
     * @param gegrLat ?
     * @param gegrLon ?
     * @param cityID id of the city, the station's in
     * @param addressStreet station address
     */
    public Station(int id, String name, float gegrLat, float gegrLon, int cityID, String addressStreet) {
        this.id = id;
        this.name = name;
        this.gegrLat = gegrLat;
        this.gegrLon = gegrLon;
        this.cityID = cityID;
        this.addressStreet = addressStreet;
    }

    /**
     * Adds new data to sensor
     * @param sensor new Sensor
     */
    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

    /**
     * Checks if stations are equal.
     * @param obj the other station
     * @return true if every field (without index and sensors) in the other station has the same value as in this station, false otherwise
     */
    public boolean equals(Object obj) {
        Station that = (Station) obj;
        return (this.id == that.id &&
                this.name.equals(that.name) &&
                this.gegrLat == that.gegrLat &&
                this.gegrLon == that.gegrLon &&
                this.cityID == that.cityID &&
                this.addressStreet.equals(that.addressStreet));
    }

    /**
     * Creates string representation of Station.
     * @return string representing station (no sensor printed)
     */
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
