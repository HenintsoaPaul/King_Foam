package stock;

import bean.TypeObjet;

import java.sql.Connection;

public class TypeMvtStock extends TypeObjet {

    public TypeMvtStock() {
        this.setNomTable( "type_mvt_stock" );
    }

    @Override
    public void construirePK( Connection c )
            throws Exception {
        this.preparePk( "TYPMVST", "GET_SEQ_TYPE_MVT_STOCK" );
        this.setId( makePK( c ) );
    }
}
