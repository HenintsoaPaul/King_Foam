package stock;

public class MvtStockDetailLib extends MvtStockDetail {

    String val_usuel;
    double prix_vente;

    public MvtStockDetailLib() {
        this.setNomTable( "mvt_stock_detail_lib" );
    }

    // Getters n Setters
    public String getVal_usuel() {
        return val_usuel;
    }

    public void setVal_usuel( String val_usuel ) {
        this.val_usuel = val_usuel;
    }

    public double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente( double prix_vente ) {
        this.prix_vente = prix_vente;
    }
}
