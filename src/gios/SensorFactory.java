package gios;

public class SensorFactory {

    public int id;
    public int stationID;
    public String paramName;
    public String paramFormula;
    public String paramCode;
    public int paramID;


    public Sensor createInstance() {
        return new Sensor(this.id, this.stationID, this.paramName, this.paramFormula, this.paramCode, this.paramID);
    }

}
