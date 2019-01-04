package gios;

/**
 * Class for creating cities when not all field values are known at the moment.
 * @author Bartłomiej Kuśmirek
 */
public class CityFactory {

    /**
     * city id
     */
    public int id;
    /**
     * name of the city
     */
    public String cityName;
    /**
     * name of the city's commune
     */
    public String communeName;
    /**
     * name of the district the city's in
     */
    public String districtName;
    /**
     * name of the province the city's in
     */
    public String provinceName;

    /**
     * Creates city from values in this class instance.
     * @return new City
     */
    public City createInstance() {
        return new City(this.id, this.cityName, this.communeName, this.districtName, this.provinceName);
    }

}
