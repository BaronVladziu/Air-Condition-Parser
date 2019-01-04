package gios;

import java.time.LocalDateTime;

/**
 * Structure representing float value with date.
 * @author Bartłomiej Kuśmirek
 */
public class Data {

    /**
     * date
     */
    public final LocalDateTime date;
    /**
     * float value
     */
    public final float value;

    /**
     * Constructor
     * @param date date
     * @param value float value
     */
    public Data(LocalDateTime date, float value) {
        this.date = date;
        this.value = value;
    }

    /**
     * Creates string representation of Data.
     * @return string representing Data
     */
    public String toString() {
        return value + " (" + date + ")";
    }

}
