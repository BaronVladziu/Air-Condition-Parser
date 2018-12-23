package gios;

public class Station {

    public final int id;
    public final String name;
    public final float gegrLat;
    public final float gegrLon;
    public final int cityID;
    public final String addressStreet;


    public Station(int id, String name, float gegrLat, float gegrLon, int cityID, String addressStreet) {
        this.id = id;
        this.name = name;
        this.gegrLat = gegrLat;
        this.gegrLon = gegrLon;
        this.cityID = cityID;
        this.addressStreet = addressStreet;
    }

}
