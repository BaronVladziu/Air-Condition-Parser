package calculable;

import gios.NamedDatedValue;

import java.time.LocalDateTime;

public class NamedDatedRange implements Addable<NamedDatedValue> {

    private NamedDatedValue up = new NamedDatedValue("", LocalDateTime.now(), Float.NEGATIVE_INFINITY);
    private NamedDatedValue down = new NamedDatedValue("", LocalDateTime.now(), Float.POSITIVE_INFINITY);

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
