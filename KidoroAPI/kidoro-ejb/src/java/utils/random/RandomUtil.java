package utils.random;

import cube.Cube;

public abstract class RandomUtil {
    protected Number minLong = 20, maxLong = 25;
    protected Number minLarg = 5, maxLarg = 7;
    protected Number minHaut = 10, maxHaut = 15;

    abstract double getRand( Number min, Number max );

    public void setRandDimensions( Cube cube ) {
        cube.setLongueur( getRand( minLong, maxLong ) );
        cube.setLargeur( getRand( minLarg, maxLarg ) );
        cube.setHauteur( getRand( minHaut, maxHaut ) );
    }
}
