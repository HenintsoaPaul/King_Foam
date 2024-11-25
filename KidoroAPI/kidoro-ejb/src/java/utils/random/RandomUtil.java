package utils.random;

import cube.Cube;
import cube.bloc.Bloc;
import holiday.Holiday;
import session.ISessionKidoroEJB;
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

    public Bloc[] getRandomData( int nbBlocsToGenerate, Date startDate, Date endDate, ISessionKidoroEJB session )
            throws Exception {
        Bloc[] blocs = new Bloc[ nbBlocsToGenerate ];

        // WARNING: get moyenne pr
        double moyennePrVolumique = session.getMoyennePrPratiqueVolumique(),
                marge = 10,
                moyennePrVolumiqueApresVariation = this.getPrAvecVariation( moyennePrVolumique, marge );

        List<Holiday> holidayList = session.getHolidays();
        Bloc b;
        for ( int i = 0; i < nbBlocsToGenerate; i++ ) {
            b = this.getRandomBloc( startDate, endDate, holidayList );
            b.setPrix_revient_pratique( moyennePrVolumiqueApresVariation * b.getVolume() );
            blocs[ i ] = b;
        }

        return blocs;
    }

    public double getPrAvecVariation( double pRevient, double marge ) {
        double max = pRevient + ( pRevient * ( marge / 100 ) ),
                min = pRevient - ( pRevient * ( marge / 100 ) );
        return RandomDoubleUtil.getRandDouble( min, max );
    }
}
