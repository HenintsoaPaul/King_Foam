import holiday.Holiday;
import holiday.IHolidayEJB;
import utils.EJBGetter;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "holidays" )
public class HolidayServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            IHolidayEJB ejb = EJBGetter.getHolidayEJB();
            List<Holiday> list = ejb.getAll();

            resp.getWriter().println( gson.toJson( list ) );

        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }
}
