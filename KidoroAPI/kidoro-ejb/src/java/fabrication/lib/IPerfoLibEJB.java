package fabrication.lib;

import javax.ejb.Remote;

@Remote
public interface IPerfoLibEJB {

    PerfoLib[] getAll()
            throws Exception;
}
