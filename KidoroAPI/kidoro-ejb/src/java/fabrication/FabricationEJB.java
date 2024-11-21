package fabrication;

import bean.CGenUtil;
import cube.bloc.Bloc;
import session.ISessionKidoroEJB;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@Stateless
public class FabricationEJB implements IFabricationEJB {

    private double getPrTheoriqueVolumique( Date daty, FormuleFabrication[] formuleFabrications, double volumeBloc, Connection conn )
            throws Exception {
        try {
            conn.setAutoCommit( false );

            double prPratique = 0;
            for ( FormuleFabrication ffConsommable : formuleFabrications ) {
                String idConsommable = ffConsommable.getId_consommable();
                AchatConsommable[] achats = getAchatConsommables( daty, idConsommable, conn );
                if ( achats == null ) {
                    throw new Exception( "Aucun stock restant pour le consommable \"" + idConsommable + "\"" );
                }
                prPratique += ffConsommable.getCoutFabrication( achats, volumeBloc, conn );
            }

            return prPratique;
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    private AchatConsommable[] getAchatConsommables( Date daty, String idConsommable, Connection conn )
            throws Exception {
        AchatConsommable ac = new AchatConsommable();
        ac.setId_consommable( idConsommable );
        String datyStr = "TO_DATE('" + daty.toString() + "', 'YYYY-MM-DD')";
        String apresWhere = " and reste > 0 and daty <= " + datyStr + " order by daty, id";
        AchatConsommable[] arr = ( AchatConsommable[] ) CGenUtil.rechercher( ac, null, null, conn, apresWhere );
        return arr.length > 0 ? arr : null;
    }

    // ------------------------------------
    private void optim( ISessionKidoroEJB session, Connection conn )
            throws Exception {
        PreparedStatement pstmt = conn.prepareStatement( "UPDATE bloc SET prix_revient_theorique = ? WHERE id = ?" );
        FormuleFabrication[] formuleFabrications = session.getFormulesFabrication();
        int batchSize = 20000, anatyBatch = 0;

        for ( Bloc bloc : session.getBlocs() ) {
            double prTheorique = getPrTheoriqueVolumique( bloc.getDaty_entree(), formuleFabrications, bloc.getVolume(), conn );

            pstmt.setDouble( 1, prTheorique );
            pstmt.setString( 2, bloc.getId() );
            pstmt.addBatch();
            anatyBatch++;

            if ( anatyBatch == batchSize ) {
                pstmt.executeBatch();
                pstmt.clearBatch();
                anatyBatch = 0;
            }
        }
        if ( anatyBatch > 0 ) {
            pstmt.executeBatch();
            pstmt.clearBatch();
        }
    }

    @Override
    public void doUpdateFabrications( ISessionKidoroEJB session )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            optim( session, conn );

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
