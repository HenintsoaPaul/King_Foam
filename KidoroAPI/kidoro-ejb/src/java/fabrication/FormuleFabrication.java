package fabrication;

import bean.ClassMAPTable;

import java.io.Serializable;
import java.sql.Connection;

public class FormuleFabrication extends ClassMAPTable implements Serializable {

    String id;
    double qte;
    String id_consommable;

    // Constr
    public FormuleFabrication() {
        this.setNomTable( "formule_fabrication" );
    }

    public double getCoutFabricationOptimized( AchatConsommable[] achats, double volumeBloc )
            throws Exception {
        double coutFabrication = 0;
        double qteBesoin = this.getQte() * volumeBloc;
        for ( int i = 0; qteBesoin > 0; i++ ) {
            if ( i == achats.length ) {
                throw new Exception( "Achats en stock insuffisants. Qte reste: " + qteBesoin );
            }
            double qteMiala, qteReste, qteDispo = achats[i].getReste();
            if ( qteBesoin >= qteDispo ) {
                qteMiala = qteDispo;
                qteReste = 0;
            } else {
                qteMiala = qteBesoin;
                qteReste = qteDispo - qteBesoin;
            }
            // Utilisation du miala
            qteBesoin -= qteMiala;
            coutFabrication += qteMiala * achats[i].getPu();
            // Update reste en stock
            achats[i].setReste( qteReste );
        }
        return coutFabrication;
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
