package etatStock;

import bean.ClassMAPTable;

public abstract class StockBloc extends ClassMAPTable {

    String id_bloc, id_usuel;
    double pv_usuel;
    double vol_bloc, vol_usuel;
    int qte_produit;
    double pr_bloc, pv_bloc;

    // Getters n Setters
    public String getId_bloc() {
        return id_bloc;
    }

    public void setId_bloc( String id_bloc ) {
        this.id_bloc = id_bloc;
    }

    public String getId_usuel() {
        return id_usuel;
    }

    public void setId_usuel( String id_usuel ) {
        this.id_usuel = id_usuel;
    }

    public double getPv_usuel() {
        return pv_usuel;
    }

    public void setPv_usuel( double pv_usuel ) {
        this.pv_usuel = pv_usuel;
    }

    public double getVol_bloc() {
        return vol_bloc;
    }

    public void setVol_bloc( double vol_bloc ) {
        this.vol_bloc = vol_bloc;
    }

    public double getVol_usuel() {
        return vol_usuel;
    }

    public void setVol_usuel( double vol_usuel ) {
        this.vol_usuel = vol_usuel;
    }

    public int getQte_produit() {
        return qte_produit;
    }

    public void setQte_produit( int qte_produit ) {
        this.qte_produit = qte_produit;
    }

    public double getPr_bloc() {
        return pr_bloc;
    }

    public void setPr_bloc( double pr_bloc ) {
        this.pr_bloc = pr_bloc;
    }

    public double getPv_bloc() {
        return pv_bloc;
    }

    public void setPv_bloc( double pv_bloc ) {
        this.pv_bloc = pv_bloc;
    }

    // Overrides
    @Override
    public String getTuppleID() {
        return "";
    }

    @Override
    public String getAttributIDName() {
        return "";
    }
}
