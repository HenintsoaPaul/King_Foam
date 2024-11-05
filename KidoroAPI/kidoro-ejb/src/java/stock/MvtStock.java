package stock;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class MvtStock extends ClassMAPTable {

    String id, designation;
    Date daty;
    double prix_revient_volumique;
    String id_origine, id_type_mvt_stock;

    // Constr
    public MvtStock() {
        this.setNomTable( "mvt_stock" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation( String designation ) {
        this.designation = designation;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty( Date daty ) {
        this.daty = daty;
    }

    public double getPrix_revient_volumique() {
        return prix_revient_volumique;
    }

    public void setPrix_revient_volumique( double p ) {
        if ( p <= 0 ) throw new IllegalArgumentException( "Prix_revient_volumique must be > 0" );
        this.prix_revient_volumique = p;
    }

    public String getId_origine() {
        return id_origine;
    }

    public void setId_origine( String id_origine ) {
        this.id_origine = id_origine;
    }

    public String getId_type_mvt_stock() {
        return id_type_mvt_stock;
    }

    public void setId_type_mvt_stock( String id_type_mvt_stock ) {
        this.id_type_mvt_stock = id_type_mvt_stock;
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
        this.preparePk( "MVST", "GET_SEQ_MVT_STOCK" );
        this.setId( makePK( c ) );
    }
}
