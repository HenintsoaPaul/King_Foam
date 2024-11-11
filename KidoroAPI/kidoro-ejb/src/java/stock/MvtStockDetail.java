package stock;

import bean.CGenUtil;
import bean.ClassMAPTable;
import cube.usuel.MyUsuel;
import utils.EJBGetter;
import cube.usuel.Usuel;

import java.sql.Connection;

public class MvtStockDetail extends ClassMAPTable {

    String id;
    int entree, sortie;
    String id_usuel, id_mvt_stock;

    // Constr
    public MvtStockDetail() {
        this.setNomTable( "mvt_stock_detail" );
    }

    public static MvtStockDetail creerFromUsuel( MyUsuel myUsuel )
            throws Exception {
        Usuel u = EJBGetter.getUsuelEJB().getByVal( myUsuel.getVal_usuel() );
        if ( u == null ) return null;

        MvtStockDetail mv = new MvtStockDetail();
        mv.setEntree( myUsuel.getQuantite() );
        mv.setSortie( 0 );
        mv.setId_usuel( u.getId() );
        return mv;
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public int getEntree() {
        return entree;
    }

    public void setEntree( int entree ) {
        this.entree = entree;
    }

    public int getSortie() {
        return sortie;
    }

    public void setSortie( int sortie ) {
        this.sortie = sortie;
    }

    public MvtStock getMere( Connection conn )
            throws Exception {
        MvtStock mv = new MvtStock();
        mv.setId( this.getId_mvt_stock() );
        MvtStock[] arr = ( MvtStock[] ) CGenUtil.rechercher( mv, null, null, conn, "" )[ 0 ];
        return arr.length > 0 ? arr[ 0 ] : null;
    }

    public double getPrixRevientVolumique( Connection conn )
            throws Exception {
        return getMere( conn ).getPrix_revient_volumique();
    }

    public String getId_usuel() {
        return id_usuel;
    }

    public void setId_usuel( String id_usuel ) {
        this.id_usuel = id_usuel;
    }

    public String getId_mvt_stock() {
        return id_mvt_stock;
    }

    public void setId_mvt_stock( String id_mvt_stock ) {
        this.id_mvt_stock = id_mvt_stock;
    }

    // Overrides
    @Override
    public String getTuppleID() {
        return this.id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public void construirePK( Connection c )
            throws Exception {
        this.preparePk( "MVSTDET", "GET_SEQ_MVT_STOCK_DETAIL" );
        this.setId( makePK( c ) );
    }
}
