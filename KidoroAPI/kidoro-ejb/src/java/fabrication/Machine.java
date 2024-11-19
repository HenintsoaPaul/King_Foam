package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Machine extends ClassMAPTable {

    String id;
    double val;

    // Constr
    public Machine() {
        this.setNomTable( "machine" );
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
        this.preparePk( "MACH", "GET_SEQ_MACHINE" );
        this.setId( makePK( c ) );
    }
}
