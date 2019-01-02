package gios;

public class IndexFactory {

    public IndexValue st;
    public IndexValue so2;
    public IndexValue no2;
    public IndexValue co;
    public IndexValue pm10;
    public IndexValue pm25;
    public IndexValue o3;
    public IndexValue c6h6;

    public Index createInstance() {
        return new Index(this.st, this.so2, this.no2, this.co, this.pm10, this.pm25, this.o3, this.c6h6);
    }

}
