package cube.usuel;

import bean.CGenUtil;

import javax.ejb.Stateless;

@Stateless
public class UsuelEJB implements IUsuelEJB {

    @Override
    public Usuel[] getAll()
            throws Exception {
        return ( Usuel[] ) CGenUtil.rechercher( new Usuel(), null, null, "" );
    }

    @Override
    public Usuel getByVal( String val )
            throws Exception {
        Usuel u = new Usuel();
        u.setVal( val );
        return ( Usuel ) CGenUtil.rechercher( u, null, null, "" )[0];
    }

    @Override
    public UsuelLib[] getAllLib( boolean desc )
            throws Exception {
        String apresWhere = "";
        if ( desc ) apresWhere = " order by rapport desc";
        return ( UsuelLib[] ) CGenUtil.rechercher( new UsuelLib(), null, null, apresWhere );
    }

    @Override
    public UsuelLib getMaxRapport()
            throws Exception {
        return getAllLib( true )[0];
    }
}
