package calculable;

/**
 * Class for calculating mean of values, that are not known all at once.
 * @author Bartłomiej Kuśmirek
 */
public class Mean implements AddCalculable<Float> {

    private float sum;
    private int counter;

    /**
     * Adds value to calculate mean from.
     * @param value added value
     */
    public void add(Float value) {
        sum += value;
        counter++;
    }

    /**
     * Calculates and returns mean.
     * @return mean
     */
    public Float calculate() {
        return sum/counter;
    }

}
