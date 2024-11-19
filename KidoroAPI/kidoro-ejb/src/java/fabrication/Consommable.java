package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Consommable extends ClassMAPTable {

    String id;
    double val;
    String id_unite;

    // Constr
    public Consommable() {
        this.setNomTable( "unite" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public double getVal() {
        return val;
    }

    public void setVal( double val ) {
        this.val = val;
    }

    public String getId_unite() {
        return id_unite;
    }

    public void setId_unite( String id_unite ) {
        this.id_unite = id_unite;
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
        this.preparePk( "CONS", "GET_SEQ_CONSOMMABLE" );
        this.setId( makePK( c ) );
    }
}
