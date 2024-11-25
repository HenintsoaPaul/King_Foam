import cube.bloc.Bloc;
import holiday.Holiday;
import utils.EJBGetter;
import utils.random.RandomIntUtil;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet( "random" )
public class RandomServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            RandomIntUtil randomIntUtil = new RandomIntUtil();
            Date startDate = Date.valueOf( "2022-01-01" );
            Date endDate = Date.valueOf( "2024-12-31" );

            List<Bloc> blocs = new ArrayList<>();
            List<Holiday> holidayList = EJBGetter.getHolidayEJB().getAll();

            double moyennePr = randomIntUtil.getMoyennePr();

            double prRevientVolumique = 500,
                    marge = 10,
                    prRehetra = randomIntUtil.getPrAvecVariation( prRevientVolumique, marge );

            Bloc b;
            for ( int i = 0; i < 2; i++ ) {
                b = randomIntUtil.getRandomBloc( startDate, endDate, holidayList );
                b.setPrix_revient_pratique( prRehetra * b.getVolume() );
                blocs.add( b );
            }

            resp.getWriter().println( gson.toJson( blocs ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }
}
