package cube.bloc;

import bean.CGenUtil;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
    public void saveAllByQuery( Bloc[] blocs )
            throws Exception {
        int total = 0;
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            String sqlInsert = "INSERT INTO bloc (daty_entree, longueur, largeur, hauteur, PRIX_REVIENT_PRATIQUE, id_machine, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement( sqlInsert );

            int batchSize = 50000, anatyBatch = 0;
            for ( Bloc b : blocs ) {
                pstmt.setDate( 1, b.getDaty_entree() );
                pstmt.setDouble( 2, b.getLongueur() );
                pstmt.setDouble( 3, b.getLargeur() );
                pstmt.setDouble( 4, b.getHauteur() );
                pstmt.setDouble( 5, b.getPrix_revient_pratique() );
                pstmt.setString( 6, b.getId_machine() );

                b.construirePK( conn );
                pstmt.setString( 7, b.getId() );

                pstmt.addBatch();
                anatyBatch++;

                if ( anatyBatch >= batchSize ) {
                    total += batchSize;
                    System.out.println( "nanao insert " + anatyBatch + " | total = " + total );
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    anatyBatch = 0;
                }
            }

            if ( anatyBatch > 0 ) {
                total += anatyBatch;
                System.out.println( "nanao insert " + anatyBatch + " | total = " + total );
                pstmt.executeBatch();
                pstmt.clearBatch();
            }
            conn.commit();
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
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
