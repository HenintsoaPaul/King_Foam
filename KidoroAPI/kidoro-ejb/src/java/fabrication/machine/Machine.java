package fabrication.machine;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Machine extends ClassMAPTable {

    String id;
    String val;

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

    public String getVal() {
        return val;
    }

    public void setVal( String val ) {
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
