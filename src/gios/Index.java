package gios;

/**
 * Structure representing air quality index.
 * @author Bartłomiej Kuśmirek
 */
public class Index {

    /**
     * values of different parameters in the order as in Parameter enum
     */
    public final IndexValue[] values;

    /**
     * Constructor
     * @param values array of length of Parameter with values in order as in Parameter enum
     */
    public Index(final IndexValue[] values) {
        this.values = values;
    }

    /**
     * Creates string representation of Index.
     * @return string representing index
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Parameter param : Parameter.values()) {
            result.append(param + ": " + values[param.ordinal()] + "\n");
        }
        return result.toString();
    }

    /**
     * Checks if indicies are equal.
     * @param obj the other index
     * @return true if every field in the other index has the same value as in this index, false otherwise
     */
    public boolean equals(Object obj) {
        Index that = (Index) obj;
        if (this.values.length != that.values.length) return false;
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                if (that.values[i] != null) return false;
            } else {
                if (!this.values[i].equals(that.values[i])) return false;
            }
        }
        return true;
    }

}
