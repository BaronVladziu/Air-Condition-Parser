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

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        IndexValue that = (IndexValue) obj;
        return (this.index.equals(that.index) &&
                this.calcDate.equals(that.calcDate) &&
                this.sourceDataDate.equals(that.sourceDataDate));
    }

}
