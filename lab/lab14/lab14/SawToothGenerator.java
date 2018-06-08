package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

/**
 * Created by Zhenye Na on Jun, 2018
 */

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state % period);
    }

    /** private helper method */
    private double normalize(double num) {
        return -1 + 2 * num / (period - 1);
    }
}
