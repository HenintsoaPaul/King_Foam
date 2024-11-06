package cube.bloc;

import bean.CGenUtil;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;

@Stateless
public class BlocEJB implements IBlocEJB {

    @Override
    public int add( Bloc bloc )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            int r = bloc.insertToTable( conn );

            conn.close();
            return r;
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if ( conn != null ) conn.close();
        }
    }

    @Override
    public Bloc[] getAll()
            throws Exception {
        return ( Bloc[] ) CGenUtil.rechercher( new Bloc(), null, null, "" );
    }
}
