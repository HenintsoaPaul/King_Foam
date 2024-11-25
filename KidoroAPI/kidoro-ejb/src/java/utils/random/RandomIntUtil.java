package utils.random;

import java.util.Random;

public class RandomIntUtil extends RandomUtil {

    /**
     * Returns a random number in the specified interval.
     *
     * @param min The lower bound of the interval (inclusive).
     * @param max The upper bound of the interval (exclusive).
     * @return A random number in the interval [min, max).
     */
    public static int getRandInt( int min, int max ) {
        if ( min >= max ) {
            throw new IllegalArgumentException( "min must be less than max" );
        }
        return new Random().nextInt( max - min ) + min;
    }

    @Override
    double getRand( Number min, Number max ) {
        return getRandInt( ( Integer ) min, ( Integer ) max );
    }
}
