package cube.bloc;

import cube.Cube;
import transformation.MyTransfo;
import utils.DateUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;

public class Bloc extends Cube implements Serializable {

    String id;
    Date daty_entree;
    Date daty_sortie;
    double prix_revient;
    String id_bloc_mere;

    // Constr
    public Bloc() {
        this.setNomTable( "bloc" );
    }

    public static Bloc creerBlocFilleFromMyTransfo( MyTransfo myTransfo, double prixRevientVolumique )
            throws ParseException {
        Bloc bFille = new Bloc();
        bFille.setDaty_entree( DateUtil.strToDate( myTransfo.getDaty() ) );
        bFille.setId_bloc_mere( myTransfo.getId_bloc() );

        bFille.setLongueur( myTransfo.getLongueur() );
        bFille.setLargeur( myTransfo.getLargeur() );
        bFille.setHauteur( myTransfo.getHauteur() );

        bFille.setPrix_revientFromPrv( prixRevientVolumique );
        return bFille;
    }

    // Controle
    public void controler()
            throws Exception {
        if ( this.getVolume() <= 0 ) throw new Exception( "Volume must be > 0" );
        if ( this.getPrix_revient() <= 0 ) throw new Exception( "Prix_revient must be > 0" );
    }

    // Getters n Setters
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Date getDaty_sortie() {
        return daty_sortie;
    }

    public void setDaty_sortie( Date daty_sortie ) {
        this.daty_sortie = daty_sortie;
    }

    public Date getDaty_entree() {
        return daty_entree;
    }

    public void setDaty_entree( Date daty_entree ) {
        this.daty_entree = daty_entree;
    }

    public double getPrix_revient() {
        return prix_revient;
    }

    public void setPrix_revient( double prix_revient ) {
        if ( prix_revient <= 0 ) throw new IllegalArgumentException( "prixRevient must be > 0" );
        this.prix_revient = prix_revient;
    }

    /**
     * Set prixRevient a partir de prixRevientVolumique
     * @param prixRevientVolumique
     */
    public void setPrix_revientFromPrv( double prixRevientVolumique ) {
        if ( prixRevientVolumique <= 0 ) throw new IllegalArgumentException( "prixRevientVolumique must be > 0" );
        this.prix_revient = this.getVolume() * prixRevientVolumique;
    }

    public String getId_bloc_mere() {
        return id_bloc_mere;
    }

    public void setId_bloc_mere( String id_bloc_mere ) {
        this.id_bloc_mere = id_bloc_mere;
    }

    // Advanced Getters
    public double getPrixRevientVolumique() {
        return this.getPrix_revient() / this.getVolume();
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
        this.preparePk( "BLOC", "GET_SEQ_BLOC" );
        this.setId( makePK( c ) );
    }

    @Override
    public int insertToTable( Connection cDb )
            throws Exception {
        this.controler();
        return super.insertToTable( cDb );
    }
}
