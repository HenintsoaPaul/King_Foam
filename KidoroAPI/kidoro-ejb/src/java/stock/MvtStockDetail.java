package stock;

import bean.ClassMAPTable;
import cube.usuel.MyUsuel;
import cube.usuel.Usuel;
import utils.EJBGetter;

import java.sql.Connection;

public class MvtStockDetail extends ClassMAPTable {

    String id;
    int entree, sortie;
    double prix_revient;
    String id_usuel, id_mvt_stock;

    // Constr
    public MvtStockDetail() {
        this.setNomTable( "mvt_stock_detail" );
    }

    public static MvtStockDetail creerFromUsuel( MyUsuel myUsuel, double prixRevientVolumique )
            throws Exception {
        Usuel usuel = EJBGetter.getUsuelEJB().getByVal( myUsuel.getVal_usuel() );
        int qte = myUsuel.getQuantite();

        MvtStockDetail mv = new MvtStockDetail();
        mv.setEntree( qte );
        mv.setSortie( 0 );

        mv.setPrix_revient( usuel.getVolume() * qte * prixRevientVolumique );
        mv.setId_usuel( usuel.getId() );

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

    public double getPrix_revient() {
        return prix_revient;
    }

    public void setPrix_revient( double prix_revient ) {
        if ( prix_revient <= 0 ) throw new IllegalArgumentException( "prixRevient must be > 0" );
        this.prix_revient = prix_revient;
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
