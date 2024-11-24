import cube.bloc.Bloc;
import utils.random.RandomIntUtil;
import utils.random.RandomUtil;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

            Bloc b;
            List<Bloc> l = new ArrayList<Bloc>();
            for ( int i = 0; i < 3; i++ ) {
                b = new Bloc();
                new RandomIntUtil().setRandDimensions( b );
                l.add( b );
            }
            resp.getWriter().println( gson.toJson( l ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }
}
