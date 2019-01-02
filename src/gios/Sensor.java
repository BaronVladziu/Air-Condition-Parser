package gios;

import java.util.LinkedList;
import java.util.List;

public class Sensor {

    public final int id;
    public final int stationID;
    public final String paramName;
    public final String paramFormula;
    public final Parameter paramCode;
    public final int paramID;

    public final List<Data> data = new LinkedList<>();


    public Sensor(int id, int stationID, String paramName, String paramFormula, String paramCode, int paramID) {
        this.id = id;
        this.stationID = stationID;
        this.paramName = paramName;
        this.paramFormula = paramFormula;
        this.paramCode = Parameter.valueOf(paramCode.toUpperCase());
        this.paramID = paramID;
    }

    public void addData(Data data) {
        this.data.add(data);
    }

    public boolean equals(Object obj) {
        Sensor that = (Sensor) obj;
        return (this.id == that.id &&
                this.stationID == that.stationID &&
                this.paramName.equals(that.paramName) &&
                this.paramFormula.equals(that.paramFormula) &&
                this.paramCode == that.paramCode &&
                this.paramID == that.paramID);
    }

    public String toString() {
        return "Sensor id: " + id + "\n" +
                "Station id: " + stationID + "\n" +
                "Parameter name: " + paramName + "\n" +
                "Parameter formula: " + paramFormula + "\n" +
                "Parameter code: " + paramCode + "\n" +
                "Parameter ID: " + paramID;
    }

}
