package calculable;

import gios.NamedDatedValue;

import java.time.LocalDateTime;

/**
 * Class for calculating and storing range of NamedDatedValues.
 * @author Bartłomiej Kuśmirek
 */
public class NamedDatedRange implements Addable<NamedDatedValue> {

    private NamedDatedValue up = new NamedDatedValue("", LocalDateTime.now(), Float.NEGATIVE_INFINITY);
    private NamedDatedValue down = new NamedDatedValue("", LocalDateTime.now(), Float.POSITIVE_INFINITY);

    /**
     * Adds new value to range, potentially widening range.
     * @param value added value
     */
    public void add(NamedDatedValue value) {
        if (value.value > up.value) {
            up = value;
        }
        if (value.value < down.value) {
            down = value;
        }
    }

    /**
     * Returns highest of given values.
     * @return highest value
     */
    public NamedDatedValue calculateUp() {
        return up;
    }

    /**
     * Returns lowest of given values.
     * @return lowest value
     */
    public NamedDatedValue calculateDown() {
        return down;
    }

}
