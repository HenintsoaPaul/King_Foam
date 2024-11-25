package fabrication.lib;

import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PerfoLibEJB implements IPerfoLibEJB {

    @Override
    public PerfoLib[] getAll()
            throws Exception {
        return fufu();
    }

    @Override
    public PerfoLib[] getByYear( String year )
            throws Exception {
        return yeaa( year );
    }

    PerfoLib[] fufu()
            throws SQLException {
        Connection c = null;
        Statement cmd = null;
        ResultSet dr = null;
        try {
            c = new UtilDB().GetConn();
            String query = "select * from PERFO_LIB";
            cmd = c.createStatement( 1004, 1008 );
            dr = cmd.executeQuery( query );

            return map( dr );
        } catch ( Exception x ) {
            x.printStackTrace();
            throw x;
        } finally {
            if ( dr != null ) dr.close();
            if ( cmd != null ) cmd.close();
            if ( c != null ) c.close();
        }
    }

    PerfoLib[] yeaa( String year )
            throws SQLException {
        Connection c = null;
        Statement cmd = null;
        ResultSet dr = null;
        try {
            c = new UtilDB().GetConn();
            String query = "select ID_MACHINE,\n" +
                    "       sum(LONGUEUR * LARGEUR * HAUTEUR)                        as vol_total,\n" +
                    "       sum(PRIX_REVIENT_THEORIQUE)                              as sum_pr_theorique,\n" +
                    "       sum(PRIX_REVIENT_PRATIQUE)                               as sum_pr_pratique,\n" +
                    "       sum(PRIX_REVIENT_THEORIQUE) - sum(PRIX_REVIENT_PRATIQUE) as diff_th_reel\n" +
                    "from bloc b\n" +
                    "where b.ID_MACHINE is not null\n" +
                    "  and extract(year from b.DATY_ENTREE) = " + year + "\n" +
                    "group by ID_MACHINE\n" +
                    "order by diff_th_reel desc";
            cmd = c.createStatement( 1004, 1008 );
            dr = cmd.executeQuery( query );

            return map( dr );
        } catch ( Exception x ) {
            x.printStackTrace();
            throw x;
        } finally {
            if ( dr != null ) dr.close();
            if ( cmd != null ) cmd.close();
            if ( c != null ) c.close();
        }
    }

    PerfoLib[] map( ResultSet dr )
            throws SQLException {
        List<PerfoLib> listResult = new ArrayList<>();
        while ( dr.next() ) {
            PerfoLib perfoLib = new PerfoLib();
            perfoLib.setId_machine( dr.getString( "id_machine" ) );
            perfoLib.setSum_pr_pratique( dr.getDouble( "sum_pr_pratique" ) );
            perfoLib.setSum_pr_theorique( dr.getDouble( "sum_pr_theorique" ) );
            perfoLib.setDiff_th_reel( dr.getDouble( "diff_th_reel" ) );
            perfoLib.setVol_total( dr.getDouble( "vol_total" ) );
            listResult.add( perfoLib );
        }
        return listResult.toArray( new PerfoLib[ 0 ] );
    }
}
