package fabrication.lib;

import bean.CGenUtil;

import javax.ejb.Stateless;

@Stateless
public class PerfoLibEJB implements IPerfoLibEJB{

    @Override
    public PerfoLib[] getAll()
            throws Exception {
        return ( PerfoLib[] ) CGenUtil.rechercher( new PerfoLib(), null, null, "" );
    }
}
