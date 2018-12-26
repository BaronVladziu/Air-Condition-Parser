package gios;

import java.util.Date;

public class IndexValue {

    public final String index;
    public final Date calcDate;
    public final Date sourceDataDate;

    public IndexValue(String index, Date calcDate, Date sourceDataDate) {
        this.index = index;
        this.calcDate = calcDate;
        this.sourceDataDate = sourceDataDate;
    }

}
