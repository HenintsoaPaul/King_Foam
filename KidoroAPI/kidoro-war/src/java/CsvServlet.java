import fabrication.AchatConsommable;
import fabrication.IAchatConsommableEJB;
import utils.EJBGetter;
import utils.json.JsonError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "csv" )
public class CsvServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );

            String path = "D:\\ITU\\INF301-Architecture_Logicielle\\King_Foam\\achat-conso.csv";
            IAchatConsommableEJB ejb = EJBGetter.getAchatConsommableEJB();
            List<AchatConsommable> achats = ejb.loadFromCsv( path );
            ejb.saveAll( achats );

            resp.getWriter().println( gson.toJson( achats ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( e.getMessage() ) ) );
        }
    }
}
