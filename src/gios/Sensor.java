package gios;

public class Sensor {

    public final int id;
    public final int stationID;
    public final String paramName;
    public final String paramFormula;
    public final String paramCode;
    public final int paramID;


    public Sensor(int id, int stationID, String paramName, String paramFormula, String paramCode, int paramID) {
        this.id = id;
        this.stationID = stationID;
        this.paramName = paramName;
        this.paramFormula = paramFormula;
        this.paramCode = paramCode;
        this.paramID = paramID;
    }

}
