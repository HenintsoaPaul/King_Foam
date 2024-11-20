package fabrication;

import bean.CGenUtil;
import cube.bloc.Bloc;
import utilitaire.UtilDB;
import utils.DateUtil;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.Date;

@Stateless
public class FabricationEJB implements IFabricationEJB {

    private double getAvgPrPratiqueVolumique( Connection conn ) {
        // todo: get from bdd
        // ...

        double moyenne = 5.0;
        double variation = 0.1;
        return moyenne * variation;
    }

    private double getPrTheoriqueVolumique( Date daty, FormuleFabrication[] formuleFabrications, Connection conn )
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
                prPratique += ffConsommable.getCoutFabrication( achats, conn );
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
        System.out.println( datyStr );
        String apresWhere = " and reste > 0 and daty <= " + datyStr + " order by daty, id";
        AchatConsommable[] arr = ( AchatConsommable[] ) CGenUtil.rechercher( ac, null, null, conn, apresWhere );
        return arr.length > 0 ? arr : null;
    }

    // ------------------------------------
    @Override
    public void doFabrications( int nbFabrication, FormuleFabrication[] formuleFabrications )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            // TODO: machines random
            String[] machines = new String[]{ "machine1", "machine2", "machine3" };

            // TODO: daty random [2022-2024]
            String randomDaty = "2022-10-27";
            Date daty = DateUtil.strToDate( randomDaty );

            // TODO: getPrPratique en general
            double avgPrPratiqueVolumique = getAvgPrPratiqueVolumique( conn );

            for ( int i = 0; i < nbFabrication; i++ ) {
                // TODO: dimensions random
                double longueur = 5,
                        largeur = 2,
                        hauteur = 4;

                double prPratiqueVolumique = avgPrPratiqueVolumique * ( longueur * largeur * hauteur );
                double prTheoriqueVolumique = getPrTheoriqueVolumique( daty, formuleFabrications, conn );

                System.out.println( "Fabrication no: " + ( i + 1 ) );
                System.out.println( "prt: " + prPratiqueVolumique + " | th: " + prTheoriqueVolumique );

                Bloc bloc = new Bloc( daty, longueur, largeur, hauteur, prTheoriqueVolumique, prPratiqueVolumique );
                bloc.setId_machine( machines[0] );
                bloc.insertToTable( conn );
//                conn.commit();

                Fabrication f = new Fabrication( machines[ 0 ], bloc.getId(), daty );
                f.insertToTable( conn );
//                conn.commit();
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
