package gios;

/**
 * Structure representing value with name.
 * @author Bartłomiej Kuśmirek
 */
public class NamedValue implements Comparable<NamedValue> {

    /**
     * name
     */
    public final String name;
    /**
     * value
     */
    public final float value;

    /**
     * Constructor
     * @param name name
     * @param value value
     */
    public NamedValue(String name, float value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Compares two values, ignoring their names
     * @param o the other value
     * @return same as Float.compare()
     */
    @Override
    public int compareTo(NamedValue o) {
        return Float.compare(o.value, this.value);
    }

    /**
     * Creates string representation of NamedValue.
     * @return string representing value with name
     */
    public String toString() {
        return name + ": " + value;
    }

}
