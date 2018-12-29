package gios;

public class Index {

    public final IndexValue[] values;

    public Index(IndexValue st, IndexValue so2, IndexValue no2, IndexValue co, IndexValue pm10, IndexValue pm25, IndexValue o3, IndexValue c6h6) {
        this.values = new IndexValue[]{st, so2, no2, co, pm10, pm25, o3, c6h6};
    }

}
