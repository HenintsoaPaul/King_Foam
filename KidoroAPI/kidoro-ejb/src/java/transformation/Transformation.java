package transformation;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class Transformation extends ClassMAPTable {

    String id;
    Date daty;
    String id_bloc_mere, id_bloc_reste;


    // Constr
    public Transformation() {
        this.setNomTable( "transformation" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getId_bloc_mere() {
        return id_bloc_mere;
    }

    public void setId_bloc_mere( String id_bloc_mere ) {
        this.id_bloc_mere = id_bloc_mere;
    }

    public String getId_bloc_reste() {
        return id_bloc_reste;
    }

    public void setId_bloc_reste( String id_bloc_reste ) {
        this.id_bloc_reste = id_bloc_reste;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty( Date daty ) {
        this.daty = daty;
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
        this.preparePk( "TRANSF", "GET_SEQ_TRANSFORMATION" );
        this.setId( makePK( c ) );
    }
}
