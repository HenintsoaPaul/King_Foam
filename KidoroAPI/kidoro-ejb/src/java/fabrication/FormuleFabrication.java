package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;

public class FormuleFabrication extends ClassMAPTable {

    String id;
    double qte;
    String id_consommable;

    // Constr
    public FormuleFabrication() {
        this.setNomTable( "formule_fabrication" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public double getQte() {
        return qte;
    }

    public void setQte( double qte ) {
        this.qte = qte;
    }

    public String getId_consommable() {
        return id_consommable;
    }

    public void setId_consommable( String id_consommable ) {
        this.id_consommable = id_consommable;
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
        this.preparePk( "FORM", "GET_SEQ_FORMULE_FABRICATION" );
        this.setId( makePK( c ) );
    }
}
