package cube.usuel;

public class UsuelLib extends Usuel {

    double volume, rapport;

    // Constr
    public UsuelLib() {
        this.setNomTable( "usuel_lib" );
    }

    // Getters n Setters
    @Override
    public double getVolume() {
        return volume;
    }

    public void setVolume( double volume ) {
        this.volume = volume;
    }

    public double getRapport() {
        return rapport;
    }

    public void setRapport( double rapport ) {
        this.rapport = rapport;
    }
}
