package utils.random;

import bean.CGenUtil;
import cube.Cube;
import cube.bloc.Bloc;
import holiday.Holiday;
import utils.DateUtil;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public Date getRandomDate( Date start, Date end, List<Holiday> holidayList ) {
        // Generate random number of days within the interval
        long randomDays = ThreadLocalRandom.current().nextLong( 0, ( end.getTime() - start.getTime() ) / ( 1000 * 60 * 60 * 24 ) + 1 );
        long randomDateInMillis = start.getTime() + randomDays * ( 1000 * 60 * 60 * 24 );

        // Ensure the generated date doesn't exceed the end date
        if ( randomDateInMillis > end.getTime() ) {
            randomDateInMillis = end.getTime();
        }

        while ( DateUtil.isNotJrOuvrable( holidayList, new Date( randomDateInMillis ) ) ) {
            // If it's a holiday, generate another random date
            randomDays = ThreadLocalRandom.current().nextLong( 0, ( end.getTime() - start.getTime() ) / ( 1000 * 60 * 60 * 24 ) + 1 );
            randomDateInMillis = start.getTime() + randomDays * ( 1000 * 60 * 60 * 24 );

            // Ensure we don't exceed the end date
            if ( randomDateInMillis > end.getTime() ) {
                randomDateInMillis = end.getTime();
            }
        }

        return new Date( randomDateInMillis );
    }

    public Bloc getRandomBloc( Date start, Date end, List<Holiday> holidayList ) {
        Bloc bloc = new Bloc();
        bloc.setDaty_entree( getRandomDate( start, end, holidayList ) );
        this.setRandDimensions( bloc );
        return bloc;
    }

    public double getPrAvecVariation( double pRevient, double marge ) {
        double max = pRevient + ( pRevient * ( marge / 100 ) ),
                min = pRevient - ( pRevient * ( marge / 100 ) );
        return RandomDoubleUtil.getRandDouble( min, max );
    }

    public double getMoyennePr()
            throws Exception {
        String apresWhere = "";
        Bloc[] blocs = ( Bloc[] ) CGenUtil.rechercher( new Bloc(), null, null, apresWhere );
        double r = 0, vols = 0;
        for ( Bloc b:  blocs ) {
            r += b.getPrixRevientVolumique();
            vols += b.getVolume();
        }
        return r / vols;
    }
}
