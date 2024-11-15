package cube.usuel;

import utils.EJBGetter;

public class MyUsuel {

    String val_usuel;
    int quantite;

    // Getters
    public String getVal_usuel() {
        return val_usuel;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getVolume()
            throws Exception {
        Usuel usuel = EJBGetter.getUsuelEJB().getByVal( this.getVal_usuel() );
        return usuel == null ? 0 : usuel.getVolume();
    }

    // Oth
    public Usuel creerUsuel() {
        Usuel u = new Usuel();
        u.setVal( this.getVal_usuel() );
        return u;
    }
}
