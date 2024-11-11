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
    public int updatePrixRevient( Bloc bloc, double newPrix )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            bloc.updatePrixRevientMaman( conn, newPrix );

            conn.close();
            return 1;
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

    @Override
    public Bloc[] getAllInStock()
            throws Exception {
        String apresWhere = " and daty_sortie is null";
        return ( Bloc[] ) CGenUtil.rechercher( new Bloc(), null, null, apresWhere );
    }

    @Override
    public Bloc getById(String id)
            throws Exception {
        Bloc b = new Bloc();
        b.setId( id );
        Bloc[] arr = ( Bloc[] ) CGenUtil.rechercher( b, null, null, "" );
        return arr.length > 0 ? arr[0] : null;
    }

    @Override
    public Bloc[] getAllMere()
            throws Exception {
        String apresWhere = " and id_bloc_mere is null";
        return ( Bloc[] ) CGenUtil.rechercher( new Bloc(), null, null, apresWhere );
    }
}
