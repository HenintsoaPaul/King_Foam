package utils;

import java.util.Random;

public abstract class RandomUtil {

    /**
     * Returns a random number in the specified interval.
     *
     * @param lowerBound The lower bound of the interval (inclusive).
     * @param upperBound The upper bound of the interval (exclusive).
     * @return A random number in the interval [lowerBound, upperBound).
     */
    public static int getRandomNumber( int lowerBound, int upperBound ) {
        if ( lowerBound >= upperBound ) {
            throw new IllegalArgumentException( "lowerBound must be less than upperBound" );
        }

        Random random = new Random();
        return random.nextInt( upperBound - lowerBound ) + lowerBound;
    }
}
