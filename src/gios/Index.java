package gios;

public class Index {

    public final IndexValue[] values;

    public Index(IndexValue st, IndexValue so2, IndexValue no2, IndexValue co, IndexValue pm10, IndexValue pm25, IndexValue o3, IndexValue c6h6) {
        this.values = new IndexValue[]{st, so2, no2, co, pm10, pm25, o3, c6h6};
    }

    public String toString() {
        return "ST: " + values[0] + "\nSO2: " + values[1] + "\nNO2: " + values[2] + "\nCO: " + values[3] + "\nPM10: " + values[4] + "\nPM2.5: " + values[5] + "\nO3: " + values[6] + "\nC6H6: " + values[7];
    }

}
