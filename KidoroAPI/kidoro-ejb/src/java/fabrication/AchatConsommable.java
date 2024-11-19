package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class AchatConsommable extends ClassMAPTable {

    String id;
    Date daty;
    double qte, reste, pu;
    String id_consommable;

    // Constr
    public AchatConsommable() {
        this.setNomTable( "achat_consommable" );
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

    public double getQte() {
        return qte;
    }

    public void setQte( double qte ) {
        this.qte = qte;
    }

    public double getReste() {
        return reste;
    }

    public void setReste( double reste ) {
        this.reste = reste;
    }

    public double getPu() {
        return pu;
    }

    public void setPu( double pu ) {
        this.pu = pu;
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
        this.preparePk( "ACHA", "GET_SEQ_ACHAT_CONSOMMABLE" );
        this.setId( makePK( c ) );
    }
}
