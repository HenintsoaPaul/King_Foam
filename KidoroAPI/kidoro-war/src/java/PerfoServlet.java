import fabrication.IFabricationEJB;
import fabrication.lib.IPerfoLibEJB;
import session.ISessionKidoroEJB;
import utils.EJBGetter;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "perfo" )
public class PerfoServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            IPerfoLibEJB ejb = EJBGetter.getPerfoLibEJB();
            resp.getWriter().println( gson.toJson( ejb.getAll() ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }

    @Override
    protected void doOptions( HttpServletRequest req, HttpServletResponse resp ) {
        setCORS( resp );
        resp.setStatus( HttpServletResponse.SC_OK );
    }
}
