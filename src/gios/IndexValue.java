package gios;

import java.time.LocalDateTime;

/**
 * Structure representing air quality index for single parameter.
 * @author Bartłomiej Kuśmirek
 */
public class IndexValue {

    /**
     * air quality index
     */
    public final String index;
    /**
     * when index was calculated
     */
    public final LocalDateTime calcDate;
    /**
     * when data for calculation was gathered
     */
    public final LocalDateTime sourceDataDate;

    /**
     * Constructor
     * @param index air quality index
     * @param calcDate when index was calculated
     * @param sourceDataDate when data for calculation was gathered
     */
    public IndexValue(String index, LocalDateTime calcDate, LocalDateTime sourceDataDate) {
        this.index = index;
        this.calcDate = calcDate;
        this.sourceDataDate = sourceDataDate;
    }

    /**
     * Creates string representation of IndexValue.
     * @return string representing index value
     */
    public String toString() {
        return index + " (" + sourceDataDate + ")";
    }

    /**
     * Checks if indexvalues are equal.
     * @param obj the other index value
     * @return true if every field in the other index value has the same value as in this index value, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        IndexValue that = (IndexValue) obj;
        return (this.index.equals(that.index) &&
                this.calcDate.equals(that.calcDate) &&
                this.sourceDataDate.equals(that.sourceDataDate));
    }

}
