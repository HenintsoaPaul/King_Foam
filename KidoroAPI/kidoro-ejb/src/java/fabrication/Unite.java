package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Unite extends ClassMAPTable {

    String id;
    double val;
    String symbole;

    // Constr
    public Unite() {
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

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole( String symbole ) {
        this.symbole = symbole;
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
        this.preparePk( "UNIT", "GET_SEQ_UNITE" );
        this.setId( makePK( c ) );
    }
}
