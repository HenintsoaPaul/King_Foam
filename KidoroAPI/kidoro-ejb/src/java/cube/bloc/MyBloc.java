package cube.bloc;

import utils.DateUtil;

import java.text.ParseException;

public class MyBloc {

    String idBloc;
    double L, l, h;
    String daty;
    double prixRevient;

    // Getters n Setters
    public double getPrixRevient() {
        return prixRevient;
    }

    public Bloc creerBloc()
            throws ParseException {
        Bloc bloc = new Bloc();
        bloc.setLongueur( this.L );
        bloc.setLargeur( this.l );
        bloc.setHauteur( this.h );
        bloc.setPrix_revient_pratique( this.prixRevient );
        bloc.setDaty_entree( DateUtil.strToDate( this.daty ) );

        return bloc;
    }

    public Bloc creerBlocUpdate() {
        Bloc bloc = new Bloc();
        bloc.setPrix_revient_pratique( this.prixRevient );
        bloc.setId( this.idBloc );

        return bloc;
    }
}
