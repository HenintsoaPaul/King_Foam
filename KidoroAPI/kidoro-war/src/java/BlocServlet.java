import cube.bloc.Bloc;
import cube.bloc.MyBloc;
import utils.EJBGetter;
import utils.json.JsonError;
import utils.json.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "blocs" )
public class BlocServlet extends HeninServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            resp.setContentType( "application/json" );
            super.setCORS( resp );
            Object[] arr = EJBGetter.getBlocEJB().getAll();
            resp.getWriter().println( this.gson.toJson( arr ) );
        } catch ( Exception e ) {
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( this.gson.toJson( new JsonError( "Erreur interne" ) ) );
        }
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            super.setCORS( resp );
            String json = JsonUtil.getFromWeb( req );
            super.dataOk( resp );

            System.out.println( json );
            MyBloc myBloc = gson.fromJson( json, MyBloc.class );
            Bloc bloc = myBloc.creerBloc();

            EJBGetter.getBlocEJB().add( bloc );
        } catch ( Exception e ) {
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( "Erreur interne" ) ) );

            throw new RuntimeException( e );
        }
    }

    @Override
    protected void doOptions( HttpServletRequest req, HttpServletResponse resp ) {
        setCORS( resp );
        resp.setStatus( HttpServletResponse.SC_OK );
    }
}
