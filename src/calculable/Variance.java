package calculable;

import java.util.LinkedList;
import java.util.List;

public class Variance implements Addable<Float>, Calculable<Float> {

    private List<Float> values = new LinkedList<>();
    private Mean mean = new Mean();

    public void add(Float value) {
        this.values.add(value);
        this.mean.add(value);
    }

    public Float calculate() {
        float sum = 0.f;
        float mean = this.mean.calculate();
        for (float value : this.values) {
            sum += Math.pow(value - mean, 2);
        }
        return sum / this.values.size();
    }

}
