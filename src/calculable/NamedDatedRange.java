package calculable;

import gios.NamedDatedValue;

import java.util.Date;

public class NamedDatedRange implements Addable<NamedDatedValue> {

    private NamedDatedValue up = new NamedDatedValue("", new Date(), Float.NEGATIVE_INFINITY);
    private NamedDatedValue down = new NamedDatedValue("", new Date(), Float.POSITIVE_INFINITY);

    public void add(NamedDatedValue value) {
        if (value.value > up.value) {
            up = value;
        }
        if (value.value < down.value) {
            down = value;
        }
    }

    public NamedDatedValue calculateUp() {
        return up;
    }

    public NamedDatedValue calculateDown() {
        return down;
    }

}
