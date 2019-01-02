package gios;

import java.time.LocalDateTime;

public class NamedDatedValue {

    public final String name;
    public final LocalDateTime date;
    public final float value;

    public NamedDatedValue(String name, LocalDateTime date, float value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

    public String toString() {
        return name + ": " + value + " (" + date + ")";
    }

}
