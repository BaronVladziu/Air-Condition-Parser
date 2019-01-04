package gios;

/**
 * Structure representing city.
 * @author Bartłomiej Kuśmirek
 */
public class City {

    /**
     * city id
     */
    public final int id;
    /**
     * name of the city
     */
    public final String cityName;
    /**
     * name of the city's commune
     */
    public final String communeName;
    /**
     * name of the district the city's in
     */
    public final String districtName;
    /**
     * name of the province the city's in
     */
    public final String provinceName;

    /**
     * Constructor
     * @param id city id
     * @param cityName name of the city
     * @param communeName name of the city's commune
     * @param districtName name of the district the city's in
     * @param provinceName name of the province the city's in
     */
    public City(int id, String cityName, String communeName, String districtName, String provinceName) {
        this.id = id;
        this.cityName = cityName;
        this.communeName = communeName;
        this.districtName = districtName;
        this.provinceName = provinceName;
    }

    /**
     * Checks if cities are equal.
     * @param obj the other city
     * @return true if every field in the other city has the same value as in this city, false otherwise
     */
    public boolean equals(Object obj) {
        City that = (City) obj;
        return (this.id == that.id && this.cityName.equals(that.cityName) && this.communeName.equals(that.communeName) &&
                this.districtName.equals(that.districtName) && this.provinceName.equals(that.provinceName));
    }

    /**
     * Creates string representation of City.
     * @return string representing city
     */
    public String toString() {
        return "City id: " + id + "\n" +
                "City name: " + cityName + "\n" +
                "Commune name: " + communeName + "\n" +
                "District name: " + districtName + "\n" +
                "Province name: " + provinceName;
    }

}
