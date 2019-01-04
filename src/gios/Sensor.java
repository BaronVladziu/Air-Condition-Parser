package gios;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing sensor.
 * @author Bartłomiej Kuśmirek
 */
public class Sensor {

    /**
     * sensor id
     */
    public final int id;
    /**
     * station id
     */
    public final int stationID;
    /**
     * name of parameter
     */
    public final String paramName;
    /**
     * parameter formula
     */
    public final String paramFormula;
    /**
     * parameter code
     */
    public final Parameter paramCode;
    /**
     * parameter id
     */
    public final int paramID;
    /**
     * data gathered by the sensor
     */
    public final List<Data> data = new LinkedList<>();

    /**
     * Constructor
     * @param id sensor id
     * @param stationID station id
     * @param paramName name of parameter
     * @param paramFormula parameter formula
     * @param paramCode parameter code
     * @param paramID parameter id
     */
    public Sensor(int id, int stationID, String paramName, String paramFormula, String paramCode, int paramID) {
        this.id = id;
        this.stationID = stationID;
        this.paramName = paramName;
        this.paramFormula = paramFormula;
        this.paramCode = Parameter.valueOf(paramCode.toUpperCase());
        this.paramID = paramID;
    }

    /**
     * Adds new data to sensor
     * @param data new Data
     */
    public void addData(Data data) {
        this.data.add(data);
    }

    /**
     * Checks if sensors are equal.
     * @param obj the other sensor
     * @return true if every field (without data) in the other sensor has the same value as in this sensor, false otherwise
     */
    public boolean equals(Object obj) {
        Sensor that = (Sensor) obj;
        return (this.id == that.id &&
                this.stationID == that.stationID &&
                this.paramName.equals(that.paramName) &&
                this.paramFormula.equals(that.paramFormula) &&
                this.paramCode == that.paramCode &&
                this.paramID == that.paramID);
    }

    /**
     * Creates string representation of Sensor.
     * @return string representing sensor (no data printed)
     */
    public String toString() {
        return "Sensor id: " + id + "\n" +
                "Station id: " + stationID + "\n" +
                "Parameter name: " + paramName + "\n" +
                "Parameter formula: " + paramFormula + "\n" +
                "Parameter code: " + paramCode + "\n" +
                "Parameter ID: " + paramID;
    }

}
