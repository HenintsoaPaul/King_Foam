import cube.bloc.Bloc;
import cube.bloc.IBlocEJB;
import session.ISessionKidoroEJB;
import utils.EJBGetter;
import utils.random.RandomIntUtil;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

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

            ISessionKidoroEJB session = EJBGetter.getSessionKidoroEJB();

            int nbBlocs = 10;
            Bloc[] blocs = randomIntUtil.getRandomData( nbBlocs, startDate, endDate, session );
            System.out.println( "Azo ny blocs rehetra" );

            IBlocEJB blocEJB = EJBGetter.getBlocEJB();
            blocEJB.saveAllByQuery( blocs );
            System.out.println( "Vita ny insert" );

            resp.getWriter().println( gson.toJson( blocs ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }
}
