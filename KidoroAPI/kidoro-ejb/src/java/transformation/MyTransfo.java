package transformation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cube.bloc.Bloc;
import cube.usuel.MyUsuel;
import teta.Teta;
import utils.EJBGetter;

import java.util.ArrayList;
import java.util.List;

public class MyTransfo {

    String daty, id_bloc;
    double longueur, largeur, hauteur;
    MyUsuel[] usuels;

    // Getters
    public String getDaty() {
        return daty;
    }

    public String getId_bloc() {
        return id_bloc;
    }

    public double getLongueur() {
        return longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public MyUsuel[] getUsuels() {
        return usuels;
    }

    // Advanced Getters
    public double getSumVolumeUsuels()
            throws Exception {
        double volume = 0;
        for ( MyUsuel u : usuels ) {
            volume += u.getVolume() * u.getQuantite();
        }
        return volume;
    }

    public Bloc getBlockMere()
            throws Exception {
        return EJBGetter.getBlocEJB().getById( this.getId_bloc() );
    }

    /**
     * Return les myUsuel dont les quantites sont > 0
     */
    // Create
    public List<MyUsuel> getUsuelACreer() {
        List<MyUsuel> li = new ArrayList<>();
        for ( MyUsuel myUsuel : this.getUsuels() ) {
            if ( myUsuel.getQuantite() > 0 ) li.add( myUsuel );
        }
        return li;
    }

    // control
    public Bloc controller()
            throws Exception {
        this.controllerBloc();
        this.controllerUsuels();
        this.controllerReste();

        Bloc blocMere = this.getBlockMere();
        Bloc blocFille = Bloc.creerBlocFilleFromMyTransfo( this, blocMere.getPrixRevientVolumique() );
        this.controllerPerteTransfo( blocMere, blocFille );

        return blocFille;
    }

    private void controllerReste()
            throws Exception {
        if ( this.getLongueur() <= 0 ) throw new Exception( "Longueur must be > 0" );
        if ( this.getLargeur() <= 0 ) throw new Exception( "Largeur must be > 0" );
        if ( this.getHauteur() <= 0 ) throw new Exception( "Hauteur must be > 0" );
    }

    private void controllerUsuels()
            throws Exception {
        double sumQte = 0;
        for ( MyUsuel myU : this.getUsuels() ) {
            if ( myU.getVal_usuel() == null ) throw new Exception( "valUsuel is null" );
            if ( myU.getVal_usuel().isEmpty() ) throw new Exception( "valUsuel is empty" );
            sumQte += myU.getQuantite();
        }
        if ( sumQte <= 0 ) throw new Exception( "La somme des quantites transforme doit etre > 0" );
    }

    private void controllerBloc()
            throws Exception {
        if ( this.getId_bloc() == null ) throw new Exception( "Id_bloc cannot be null" );
        if ( this.getId_bloc().isEmpty() ) throw new Exception( "Id_bloc cannot be empty" );
    }

    private void controllerPerteTransfo( Bloc blocMere, Bloc blocFille )
            throws Exception {
        double sumVolumeUsuels = this.getSumVolumeUsuels(),
                volumeBlockMere = blocMere.getVolume();

        if ( volumeBlockMere < sumVolumeUsuels )
            throw new Exception( "Volume BlockMere["+volumeBlockMere+"] < Sum Volume Usuel["+sumVolumeUsuels+"]" );

        double volumeResteTheorique = volumeBlockMere - sumVolumeUsuels,
                volumeResteReel = blocFille.getVolume();

        if ( volumeResteTheorique < volumeResteReel )
            throw new Exception( "Volume reste theorique["+volumeResteTheorique+"] < Volume reste reel ["+volumeResteReel+"]" );

        Teta teta = EJBGetter.getTetaEJB().get();
        double perteTolerable = volumeResteTheorique * teta.getVal(),
                perte = Math.abs( volumeResteTheorique - volumeResteReel );

        if ( perte > perteTolerable )
            throw new Exception( "La perte de transformation n'est pas tolerable. Perte: "
                    + perte + " | Tolerence: " + perteTolerable );
    }

    // json
    public String constructJson() {
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty( "daty", this.daty );
        jsonObject.addProperty( "id_bloc", this.id_bloc );
        jsonObject.addProperty( "longueur", this.longueur );
        jsonObject.addProperty( "largeur", this.largeur );
        jsonObject.addProperty( "hauteur", this.hauteur );

        JsonArray usuelsArray = new JsonArray();
        for ( MyUsuel usuel : this.usuels ) {
            JsonObject usuelJson = new JsonObject();
            usuelJson.addProperty( "val_usuel", usuel.getVal_usuel() );
            usuelJson.addProperty( "quantite", usuel.getQuantite() );
            usuelsArray.add( usuelJson );
        }
        jsonObject.add( "usuels", usuelsArray );

        return gson.toJson( jsonObject );
    }
}
