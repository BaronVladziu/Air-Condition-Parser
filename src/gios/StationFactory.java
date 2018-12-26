package gios;

public class StationFactory {

    public int id;
    public String name;
    public float gegrLat;
    public float gegrLon;
    public int cityID;
    public String addressStreet;
    public Index index;

    public Station createInstance() {
        return new Station(this.id, this.name, this.gegrLat, this.gegrLon, this.cityID, this.addressStreet, this.index);
    }

}
