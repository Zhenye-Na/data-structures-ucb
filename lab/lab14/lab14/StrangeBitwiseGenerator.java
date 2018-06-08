package lab14;

import lab14lib.Generator;

/**
 * Created by Zhenye Na on Jun, 2018
 */
public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = state + 1;
        int weirdState = state & (state >> 4) % (int) period;
        return normalize(weirdState);
    }

    /** private helper method */
    private double normalize(double num) {
        return -1 + 2 * num / (period - 1);
    }
}
