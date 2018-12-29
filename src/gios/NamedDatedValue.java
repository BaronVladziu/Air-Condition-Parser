package gios;

import java.util.Date;

public class NamedDatedValue {

    public final String name;
    public final Date date;
    public final float value;

    public NamedDatedValue(String name, Date date, float value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

}
