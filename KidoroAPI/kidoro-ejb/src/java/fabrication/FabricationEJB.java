package fabrication;

import bean.CGenUtil;
import utilitaire.UtilDB;
import utils.EJBGetter;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.Date;

@Stateless
public class FabricationEJB implements IFabricationEJB {

    @Override
    public int add( Fabrication fabrication )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            int r = fabrication.insertToTable( conn );

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
    public double getPrixRevientPratiqueVolumique( Date daty )
            throws Exception {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            double prPratique = 0;
            FormuleFabrication[] formuleFabrications = getFormuleFabrication();
            for ( FormuleFabrication ffConsommable : formuleFabrications ) {
                String idConsommable = ffConsommable.getId_consommable();
                AchatConsommable[] achats = getAchatConsommables( daty, idConsommable, conn );
                if ( achats == null ) {
                    throw new Exception( "Aucun stock restant pour le consommable \"" + idConsommable + "\"" );
                }
                prPratique += ffConsommable.getCoutFabrication( achats, conn );
            }
            conn.commit();

            conn.close();
            return prPratique;
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if ( conn != null ) conn.close();
        }
    }

    private FormuleFabrication[] getFormuleFabrication()
            throws Exception {
        FormuleFabricationEJB ejb = ( FormuleFabricationEJB ) EJBGetter.getFormuleFabricationEJB();
        return ejb.getFormulesFabrication();
    }

    private AchatConsommable[] getAchatConsommables( Date daty, String idConsommable, Connection conn )
            throws Exception {
        AchatConsommable ac = new AchatConsommable();
        ac.setId_consommable( idConsommable );
        String apresWhere = " and reste > 0 and daty <= " + daty + " order by daty, id";
        AchatConsommable[] arr = ( AchatConsommable[] ) CGenUtil.rechercher( ac, null, null, conn, apresWhere );
        return arr.length > 0 ? arr : null;

//        AchatConsommableEJB ejb = ( AchatConsommableEJB ) EJBGetter.getAchatConsommableEJB();
//        return ejb.getAchats( daty, idConsommable );
    }
}
