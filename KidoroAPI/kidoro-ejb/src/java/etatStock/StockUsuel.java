package etatStock;

import bean.ClassMAPTable;

public class StockUsuel extends ClassMAPTable {

    String id_usuel;
    int qte_total;
    double prix_vente_total, prix_revient_total, prix_revient_avg;

    // Constr
    public StockUsuel() {
        this.setNomTable( "v_stock_usuel" );
    }

    // Getters n Setters
    public String getId_usuel() {
        return id_usuel;
    }

    public void setId_usuel( String id_usuel ) {
        this.id_usuel = id_usuel;
    }

    public int getQte_total() {
        return qte_total;
    }

    public void setQte_total( int qte_total ) {
        this.qte_total = qte_total;
    }

    public double getPrix_vente_total() {
        return prix_vente_total;
    }

    public void setPrix_vente_total( double prix_vente_total ) {
        this.prix_vente_total = prix_vente_total;
    }

    public double getPrix_revient_total() {
        return prix_revient_total;
    }

    public void setPrix_revient_total( double prix_revient_total ) {
        this.prix_revient_total = prix_revient_total;
    }

    public double getPrix_revient_avg() {
        return prix_revient_avg;
    }

    public void setPrix_revient_avg( double prix_revient_avg ) {
        this.prix_revient_avg = prix_revient_avg;
    }

    // Overrides
    @Override
    public String getTuppleID() {
        return id_usuel;
    }

    @Override
    public String getAttributIDName() {
        return "id_usuel";
    }
}
