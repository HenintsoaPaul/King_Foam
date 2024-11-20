package fabrication;

import utilitaire.UtilDB;
import utils.CSVUtil;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.util.List;

@Stateless
public class AchatConsommableEJB implements IAchatConsommableEJB {

    @Override
    public List<AchatConsommable> loadFromCsv( String filePath ) {
        return CSVUtil.loadAchatConsommable( filePath );
    }

    @Override
    public void saveAll( List<AchatConsommable> achats )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            for ( AchatConsommable achatConsommable : achats ) {
                achatConsommable.insertToTable( conn );
            }
            conn.commit();

            conn.close();
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if ( conn != null ) conn.close();
        }
    }
}
