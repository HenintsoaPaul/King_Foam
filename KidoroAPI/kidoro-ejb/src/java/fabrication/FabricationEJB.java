package fabrication;

import bean.CGenUtil;
import cube.bloc.Bloc;
import session.ISessionKidoroEJB;
import utilitaire.UtilDB;
import utils.DateUtil;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.Date;

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
    @Override
    public void doFabrications( int nbFabrication, ISessionKidoroEJB session )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            // Data Initialization
            FormuleFabrication[] formuleFabrications = session.getFormulesFabrication();
            double avgPrPratiqueVolumique = session.getMoyennePrPratiqueVolumique();

            // TODO: daty random [2022-2024]
            String randomDaty = "2022-10-27";
            Date daty = DateUtil.strToDate( randomDaty );


            for ( int i = 0; i < nbFabrication; i++ ) {
                // TODO: dimensions random
                double longueur = 5,
                        largeur = 2,
                        hauteur = 4;
                double volumeBloc = longueur * largeur * hauteur;

                double prPratique = avgPrPratiqueVolumique * volumeBloc;
                double prTheorique = getPrTheoriqueVolumique( daty, formuleFabrications, volumeBloc, conn );

                System.out.println( "Fabrication no: " + ( i + 1 ) );
                System.out.println( "prt: " + prPratique + " | th: " + prTheorique );

                Bloc bloc = new Bloc( daty, longueur, largeur, hauteur, prTheorique, prPratique );
                bloc.setId_machine( session.getRandomMachine().getId() );
                bloc.insertToTable( conn );
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

    @Override
    public void doUpdateFabrications( ISessionKidoroEJB session )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            FormuleFabrication[] formuleFabrications = session.getFormulesFabrication();
            double avgPrPratiqueVolumique = session.getMoyennePrPratiqueVolumique();

            for ( Bloc bloc : session.getBlocs() ) {
                double volumeBloc = bloc.getVolume(),
                        prPratique = avgPrPratiqueVolumique * volumeBloc,
                        prTheorique = getPrTheoriqueVolumique( bloc.getDaty_entree(), formuleFabrications, volumeBloc, conn );

                bloc.updatePrixRevient( prTheorique, prPratique, conn );
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
