import utils.EJBGetter;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = "stock/mvtStock" )
public class MvtStockDetailServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            Object[] arr = EJBGetter.getStockEJB().getAllUsuelLib();

            resp.getWriter().println( gson.toJson( arr ) );
        } catch ( Exception e ) {
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( "Erreur interne" ) ) );
        }
    }
}
