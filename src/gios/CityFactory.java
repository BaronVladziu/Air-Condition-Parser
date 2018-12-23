package gios;

public class CityFactory {

    public int id;
    public String cityName;
    public String communeName;
    public String districtName;
    public String provinceName;

    public City createInstance() {
        return new City(this.id, this.cityName, this.communeName, this.districtName, this.provinceName);
    }

}
