import utils.EJBGetter;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "usuels" )
public class UsuelServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            Object[] arr = null;
            String action = req.getParameter( "action" );
            if ( action == null ) {
                arr = EJBGetter.getUsuelEJB().getAll();
            } else if ( action.equalsIgnoreCase( "lib" ) ) {
                arr = EJBGetter.getUsuelEJB().getAllLib( true );
            }

            resp.getWriter().println( gson.toJson( arr ) );
        } catch ( Exception e ) {
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( "Erreur interne" ) ) );
        }
    }
}
