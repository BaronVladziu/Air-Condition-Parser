package gios;

import java.time.LocalDateTime;

/**
 * Structure representing value with name and date.
 * @author Bartłomiej Kuśmirek
 */
public class NamedDatedValue {

    /**
     * name
     */
    public final String name;
    /**
     * date
     */
    public final LocalDateTime date;
    /**
     * value
     */
    public final float value;

    /**
     * Constructor
     * @param name name
     * @param date date
     * @param value value
     */
    public NamedDatedValue(String name, LocalDateTime date, float value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

    /**
     * Creates string representation of NamedDatedValue.
     * @return string representing value with name and date
     */
    public String toString() {
        return name + ": " + value + " (" + date + ")";
    }

}
