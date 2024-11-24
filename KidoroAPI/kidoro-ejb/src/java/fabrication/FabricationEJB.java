package fabrication;

import cube.bloc.Bloc;
import session.ISessionKidoroEJB;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Comparator;

@Stateless
public class FabricationEJB implements IFabricationEJB {

    private double getPrTheoriqueVolumiqueOptimized( Date daty, FormuleFabrication[] formuleFabrications, AchatConsommable[] sessionAchats, double volumeBloc )
            throws Exception {
        try {
            double prPratique = 0;
            for ( FormuleFabrication ffConsommable : formuleFabrications ) {
                String idConsommable = ffConsommable.getId_consommable();
                AchatConsommable[] achats = this.getAchatConsommablesBeforeDaty( sessionAchats, daty, idConsommable );
                if ( achats == null ) {
                    throw new Exception( "Aucun stock restant pour le consommable \"" + idConsommable + "\"" );
                }
                prPratique += ffConsommable.getCoutFabricationOptimized( achats, volumeBloc );

                for ( AchatConsommable vao: achats ) {
                    for ( AchatConsommable taloha: sessionAchats ) {
                        if ( taloha.getId().equals( vao.getId() ) ) {
                            taloha.setReste( vao.getReste() );
                        }
                    }
                }
            }

            return prPratique;
        } catch ( Exception e ) {
            e.printStackTrace();
            throw e;
        }
    }

    public AchatConsommable[] getAchatConsommablesBeforeDaty( AchatConsommable[] achats, Date daty, String idConsommable ) {
        return Arrays.stream( achats )
                .filter( achat -> achat.getDaty().compareTo( daty ) <= 0
                        && achat.getReste() > 0
                        && achat.getId_consommable().equals( idConsommable ) )
                .sorted( Comparator.comparing( AchatConsommable::getDaty ) ).toArray( AchatConsommable[]::new );
    }

    // ------------------------------------
    private void optim( ISessionKidoroEJB session, Connection conn )
            throws Exception {
        PreparedStatement pstmt = conn.prepareStatement( "UPDATE bloc SET prix_revient_theorique = ? WHERE id = ?" );
        FormuleFabrication[] formuleFabrications = session.getFormulesFabrication();
        AchatConsommable[] sessionAchats = session.getAchatConsommables();
        int batchSize = 20000, anatyBatch = 0;

        for ( Bloc bloc : session.getBlocs() ) {
            double prTheorique = getPrTheoriqueVolumiqueOptimized( bloc.getDaty_entree(), formuleFabrications, sessionAchats, bloc.getVolume() );

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
        conn.commit();

        for ( AchatConsommable achat : sessionAchats ) {
            achat.updateToTable( conn );
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
