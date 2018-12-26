package gios;

public class Index {

    public final IndexValue st;
    public final IndexValue so2;
    public final IndexValue no2;
    public final IndexValue co;
    public final IndexValue pm10;
    public final IndexValue pm25;
    public final IndexValue o3;
    public final IndexValue c6h6;

    public Index(IndexValue st, IndexValue so2, IndexValue no2, IndexValue co, IndexValue pm10, IndexValue pm25, IndexValue o3, IndexValue c6h6) {
        this.st = st;
        this.so2 = so2;
        this.no2 = no2;
        this.co = co;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.o3 = o3;
        this.c6h6 = c6h6;
    }

}
