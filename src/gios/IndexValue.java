package gios;

import java.time.LocalDateTime;

public class IndexValue {

    public final String index;
    public final LocalDateTime calcDate;
    public final LocalDateTime sourceDataDate;

    public IndexValue(String index, LocalDateTime calcDate, LocalDateTime sourceDataDate) {
        this.index = index;
        this.calcDate = calcDate;
        this.sourceDataDate = sourceDataDate;
    }

    public String toString() {
        return index + " (" + sourceDataDate + ")";
    }

}
