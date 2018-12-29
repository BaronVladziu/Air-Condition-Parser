package gios;

public class NamedValue implements Comparable<NamedValue> {

    public final String name;
    public final float value;

    public NamedValue(String name, float value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int compareTo(NamedValue o) {
        return Float.compare(o.value, this.value);
    }
}
