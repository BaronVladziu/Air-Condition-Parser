package calculable;

public class Mean implements Addable<Float>, Calculable<Float> {

    private float sum;
    private int counter;

    public void add(Float value) {
        sum += value;
        counter++;
    }

    public Float calculate() {
        return sum/counter;
    }

}
