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

    public double getCoutFabrication( AchatConsommable[] achats, Connection conn )
            throws Exception {
        double coutFabrication = 0;
        double qteBesoin = this.getQte();
        for ( int i = 0; qteBesoin > 0; i++ ) {
            if ( i == achats.length ) {
                throw new Exception( "Achats en stock insuffisants. Qte reste: " + qteBesoin );
            }
            AchatConsommable achat = achats[ i ];
            double qteMiala, qteReste, qteDispo = achat.getReste();
            if ( qteBesoin >= qteDispo ) {
                qteMiala = qteDispo;
                qteReste = 0;
            } else {
                qteMiala = qteBesoin;
                qteReste = qteDispo - qteBesoin;
            }
            // Utilisation du miala
            qteBesoin -= qteMiala;
            coutFabrication += qteMiala * achat.getPu();
            // Update reste en stock
            achat.setReste( qteReste );
            achat.updateToTable( conn );
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
