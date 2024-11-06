package cube.bloc;

import utils.DateUtil;

import java.text.ParseException;

public class MyBloc {

    double L, l, h;
    String daty;
    double prixRevient;

    public Bloc creerBloc()
            throws ParseException {
        Bloc bloc = new Bloc();
        bloc.setLongueur( this.L );
        bloc.setLargeur( this.l );
        bloc.setHauteur( this.h );
        bloc.setPrix_revient( this.prixRevient );
        bloc.setDaty_entree( DateUtil.strToDate( this.daty ) );

        return bloc;
    }
}
