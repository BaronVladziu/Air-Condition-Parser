package gios;

/**
 * Class for creating sensors when not all field values are known at the moment.
 * @author Bartłomiej Kuśmirek
 */
public class SensorFactory {

    /**
     * sensor id
     */
    public int id;
    /**
     * station id
     */
    public int stationID;
    /**
     * name of parameter
     */
    public String paramName;
    /**
     * parameter formula
     */
    public String paramFormula;
    /**
     * parameter code
     */
    public String paramCode;
    /**
     * parameter id
     */
    public int paramID;

    /**
     * Creates sensor from values in this class instance.
     * @return new Sensor
     */
    public Sensor createInstance() {
        return new Sensor(this.id, this.stationID, this.paramName, this.paramFormula, this.paramCode, this.paramID);
    }

}
