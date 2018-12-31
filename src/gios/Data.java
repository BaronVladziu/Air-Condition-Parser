package gios;

import java.time.LocalDateTime;

public class Data {

    public final LocalDateTime date;
    public final float value;

    public Data(LocalDateTime date, float value) {
        this.date = date;
        this.value = value;
    }

    public String toString() {
        return value + " (" + date + ")";
    }

}
