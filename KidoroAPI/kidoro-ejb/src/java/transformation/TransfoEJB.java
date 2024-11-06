package transformation;

import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;

@Stateless
public class TransfoEJB implements ITransfoEJB {

    @Override
    public int add( Transformation transformation )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            int r = transformation.insertToTable( conn );

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
}
