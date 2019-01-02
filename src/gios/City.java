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

    public boolean equals(Object obj) {
        City that = (City) obj;
        return (this.id == that.id && this.cityName.equals(that.cityName) && this.communeName.equals(that.communeName) &&
                this.districtName.equals(that.districtName) && this.provinceName.equals(that.provinceName));
    }

    public String toString() {
        return "City id: " + id + "\n" +
                "City name: " + cityName + "\n" +
                "Commune name: " + communeName + "\n" +
                "District name: " + districtName + "\n" +
                "Province name: " + provinceName;
    }

}
