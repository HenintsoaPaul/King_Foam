package cube.bloc;

import bean.CGenUtil;
import cube.Cube;
import stock.MvtStock;
import transformation.MyTransfo;
import transformation.Transformation;
import utils.DateUtil;
import utils.EJBGetter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;

public class Bloc extends Cube implements Serializable {

    String id;
    Date daty_entree;
    Date daty_sortie;
    double prix_revient;
    String id_bloc_mere, id_bloc_base;

    // Constr
    public Bloc() {
        this.setNomTable( "bloc" );
    }

    public static Bloc creerBlocFilleFromMyTransfo( MyTransfo myTransfo, double prixRevientVolumique )
            throws ParseException {
        Bloc bFille = new Bloc();
        bFille.setDaty_entree( DateUtil.strToDate( myTransfo.getDaty() ) );
        bFille.setId_bloc_mere( myTransfo.getId_bloc() );
        bFille.setPrix_revientFromPrv( prixRevientVolumique );

        double L = myTransfo.getLongueur(),
                l = myTransfo.getLargeur(),
                h = myTransfo.getHauteur();
        if ( L == 0 && l == 0 && h == 0 ) {
            return bFille;
        }

        bFille.setLongueur( myTransfo.getLongueur() );
        bFille.setLargeur( myTransfo.getLargeur() );
        bFille.setHauteur( myTransfo.getHauteur() );
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
        System.out.println("pr_vol: " + prixRevientVolumique + " | vol: " + this.getVolume());
        this.prix_revient = this.getVolume() * prixRevientVolumique;
    }

    public String getId_bloc_mere() {
        return id_bloc_mere;
    }

    public void setId_bloc_mere( String id_bloc_mere ) {
        this.id_bloc_mere = id_bloc_mere;
    }

    public String getId_bloc_base() {
        return id_bloc_base;
    }

    public void setId_bloc_base( String id_bloc_base ) {
        this.id_bloc_base = id_bloc_base;
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

    // Alea
    public Bloc getFille( Connection conn )
            throws Exception {
        Bloc b = new Bloc();
        b.setId_bloc_mere( this.getId() );
        Bloc[] arr = ( Bloc[] ) CGenUtil.rechercher( b, null, null, conn, "" );
        return arr.length > 0 ? arr[ 0 ] : null;
    }

    public Transformation getTransfo( Connection conn )
            throws Exception {
        Transformation t = new Transformation();
        t.setId_bloc_mere( this.getId() );
        Transformation[] arr = ( Transformation[] ) CGenUtil.rechercher( t, null, null, conn, "" );
        return arr.length > 0 ? arr[ 0 ] : null;
    }

    public MvtStock getMvtStock( Connection conn, String idOrigine )
            throws Exception {
        MvtStock mv = new MvtStock();
        mv.setId_origine( idOrigine );
        return ( MvtStock ) CGenUtil.rechercher( mv, null, null, conn, "" )[ 0 ];
    }

    public void updateUsuels( Connection conn )
            throws Exception {
        Transformation t = getTransfo( conn );
        if ( t == null ) {
            System.out.println( "Mbola tsy nisy transfo" );
        } else {
            MvtStock mvtStock = getMvtStock( conn, t.getId() );
            mvtStock.updatePrixRevient( conn, this.getPrixRevientVolumique() );
        }
    }

    public void updatePrixRevientMaman( Connection conn, double newPrix )
            throws Exception {
        try {
            conn.setAutoCommit( false );

            Bloc b = EJBGetter.getBlocEJB().getById( this.getId() );

            if ( b.getId_bloc_mere() != null ) throw new Exception( "N'est pas une mere final" );

            double proportion = newPrix / b.getPrix_revient();
            b.setPrix_revient( newPrix );
            b.updateToTable( conn );

            if ( b.getDaty_sortie() != null ) b.updateUsuels( conn );

            Bloc fille = getFille( conn );
            if ( fille == null ) {
                System.out.println( "Pas de fille pour cet bloc mere" );
            } else {
                fille.updatePrixRevientFille( conn, proportion );
            }

            conn.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
            conn.rollback();
        }
    }

    public void updatePrixRevientFille( Connection conn, double proportion )
            throws Exception {
        this.setPrix_revient( this.getPrix_revient() * proportion );
        this.updateToTable( conn );

        if ( this.getDaty_sortie() != null )  this.updateUsuels( conn );

        Bloc fille = getFille( conn );
        if ( fille == null ) {
            System.out.println( "Plus de fille pour cette lignee" );
        } else {
            fille.updatePrixRevientFille( conn, proportion );
        }
    }
}
