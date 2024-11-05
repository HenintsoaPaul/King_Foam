package cube.forme;

import cube.Cube;

import java.sql.Connection;

public class Forme extends Cube {

    String id, val;
    double prix_vente;

    // Constr
    public Forme() {
        this.setNomTable( "forme" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal( String val ) {
        this.val = val;
    }

    public double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente( double prix_vente ) {
        this.prix_vente = prix_vente;
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
        this.preparePk( "FORME", "GET_SEQ_FORME" );
        this.setId( makePK( c ) );
    }
}
