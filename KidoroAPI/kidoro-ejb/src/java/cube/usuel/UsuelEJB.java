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
}
