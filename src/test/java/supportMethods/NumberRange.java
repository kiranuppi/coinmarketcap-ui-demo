package supportMethods;

public class NumberRange {
    double min;
    double max;

    public NumberRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "NumberRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
