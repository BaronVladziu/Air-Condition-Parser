package gios;

public class Index {

    public final IndexValue[] values;

    public Index(IndexValue st, IndexValue so2, IndexValue no2, IndexValue co, IndexValue pm10, IndexValue pm25, IndexValue o3, IndexValue c6h6) {
        this.values = new IndexValue[]{st, so2, no2, co, pm10, pm25, o3, c6h6};
    }

    public String toString() {
        return "ST: " + values[0] + "\nSO2: " + values[1] + "\nNO2: " + values[2] + "\nCO: " + values[3] + "\nPM10: " + values[4] + "\nPM2.5: " + values[5] + "\nO3: " + values[6] + "\nC6H6: " + values[7];
    }

    public boolean equals(Object obj) {
        Index that = (Index) obj;
        if (this.values.length != that.values.length) return false;
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                if (that.values[i] != null) return false;
            } else {
                if (!this.values[i].equals(that.values[i])) return false;
            }
        }
        return true;
    }

}
