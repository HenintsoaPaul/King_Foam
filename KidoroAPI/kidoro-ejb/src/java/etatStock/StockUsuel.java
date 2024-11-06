package etatStock;

import bean.ClassMAPTable;

public class StockUsuel extends ClassMAPTable {

    String id_usuel;
    int qte_total;
    double avg_pu_revient, pu_vente;
    double p_revient, p_vente;

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

    public double getAvg_pu_revient() {
        return avg_pu_revient;
    }

    public void setAvg_pu_revient( double avg_pu_revient ) {
        this.avg_pu_revient = avg_pu_revient;
    }

    public double getPu_vente() {
        return pu_vente;
    }

    public void setPu_vente( double pu_vente ) {
        this.pu_vente = pu_vente;
    }

    public double getP_revient() {
        return p_revient;
    }

    public void setP_revient( double p_revient ) {
        this.p_revient = p_revient;
    }

    public double getP_vente() {
        return p_vente;
    }

    public void setP_vente( double p_vente ) {
        this.p_vente = p_vente;
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
