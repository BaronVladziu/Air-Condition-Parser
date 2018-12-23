package gios;

public class City {

    public final int id;
    public final String cityName;
    public final String communeName;
    public final String districtName;
    public final String provinceName;


    public City(int id, String cityName, String communeName, String districtName, String provinceName) {
        this.id = id;
        this.cityName = cityName;
        this.communeName = communeName;
        this.districtName = districtName;
        this.provinceName = provinceName;
    }

}
