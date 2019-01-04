package calculable;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for calculating variance of values, that are not known all at once.
 * @author Bartłomiej Kuśmirek
 */
public class Variance implements AddCalculable<Float> {

    private List<Float> values = new LinkedList<>();
    private Mean mean = new Mean();

    /**
     * Adds value to calculate variance from.
     * @param value added value
     */
    public void add(Float value) {
        this.values.add(value);
        this.mean.add(value);
    }

    /**
     * Calculates and returns variance.
     * @return variance
     */
    public Float calculate() {
        float sum = 0.f;
        float mean = this.mean.calculate();
        for (float value : this.values) {
            sum += Math.pow(value - mean, 2);
        }
        return sum / this.values.size();
    }

}
