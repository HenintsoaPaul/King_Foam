package utils.random;

import java.util.Random;

public class RandomDoubleUtil extends RandomUtil {

    /**
     * Returns a random number in the specified interval.
     *
     * @param min The lower bound of the interval (inclusive).
     * @param max The upper bound of the interval (inclusive).
     * @return A random number in the interval [min, max).
     */
    public static double getRandDouble( double min, double max ) {
        if ( min >= max ) {
            throw new IllegalArgumentException( "min must be less than max" );
        }
        return min + ( new Random().nextDouble() * ( max - min ) );
    }

    @Override
    double getRand( Number min, Number max ) {
        return getRandDouble( ( Double ) min, ( Double ) max );
    }
}
