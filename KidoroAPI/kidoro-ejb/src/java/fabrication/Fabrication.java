package fabrication;

import bean.ClassMAPTable;
import cube.bloc.Bloc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;

public class Fabrication extends ClassMAPTable implements Serializable {

    String id;
    Date daty;
    String id_machine, id_bloc;

    // Constr
    public Fabrication() {
        this.setNomTable( "fabrication" );
    }

    public Fabrication( String idMachine, String idBloc, Date daty ) {
        this.setNomTable( "fabrication" );

        this.setDaty( daty );
        this.setId_bloc( idBloc );
        this.setId_machine( idMachine );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty( Date daty ) {
        this.daty = daty;
    }

    public String getId_machine() {
        return id_machine;
    }

    public void setId_machine( String id_machine ) {
        this.id_machine = id_machine;
    }

    public String getId_bloc() {
        return id_bloc;
    }

    public void setId_bloc( String id_bloc ) {
        this.id_bloc = id_bloc;
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
        this.preparePk( "FABR", "GET_SEQ_FABRICATION" );
        this.setId( makePK( c ) );
    }
}
