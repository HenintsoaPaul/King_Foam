package stock;

import bean.ClassMAPTable;

import java.sql.Connection;

public class MvtStockDetail extends ClassMAPTable {

    String id;
    int entree, sortie;
    double prix_revient;
    String id_forme, id_mvt_stock;

    // Constr
    public MvtStockDetail() {
        this.setNomTable( "mvt_stock_detail" );
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

    public String getId_forme() {
        return id_forme;
    }

    public void setId_forme( String id_forme ) {
        this.id_forme = id_forme;
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
