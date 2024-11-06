package cube;

import bean.ClassMAPTable;

public abstract class Cube extends ClassMAPTable {

    double longueur;
    double largeur;
    double hauteur;

    // Getters n Setters
    public double getLongueur() {
        return longueur;
    }

    public void setLongueur( double longueur ) {
        if ( longueur <= 0 ) throw new IllegalArgumentException( "longueur must be > 0" );
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur( double largeur ) {
        if ( largeur <= 0 ) throw new IllegalArgumentException( "largeur must be > 0" );
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur( double hauteur ) {
        if ( hauteur <= 0 ) throw new IllegalArgumentException( "hauteur must be > 0" );
        this.hauteur = hauteur;
    }

    // Others
    public double getVolume() {
        return this.getLongueur() * this.getLargeur() * this.getHauteur();
    }
}
