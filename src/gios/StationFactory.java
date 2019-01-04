package gios;

/**
 * Class for creating stations when not all field values are known at the moment.
 * @author Bartłomiej Kuśmirek
 */
public class StationFactory {

    /**
     * station id
     */
    public int id;
    /**
     * station name
     */
    public String name;
    /**
     * ?
     */
    public float gegrLat;
    /**
     * ?
     */
    public float gegrLon;
    /**
     * id of the city, the station's in
     */
    public int cityID;
    /**
     * station address
     */
    public String addressStreet;

    /**
     * Creates station from values in this class instance.
     * @return new Station
     */
    public Station createInstance() {
        return new Station(this.id, this.name, this.gegrLat, this.gegrLon, this.cityID, this.addressStreet);
    }

}
