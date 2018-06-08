package lab14;
import lab14lib.Generator;

/**
 * Created by Zhenye Na on Jun, 2018
 */

public class AcceleratingSawToothGenerator implements Generator{
    private int state;
    private int period;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state = state + 1;
        if (state % period == 0) {
            period = (int) (period * factor);
            state = 0;
        }
        return normalize(state);
    }

    /** private helper method */
    private double normalize(double num) {
        return -1 + 2 * (num % period) / (period - 1);
    }

}
