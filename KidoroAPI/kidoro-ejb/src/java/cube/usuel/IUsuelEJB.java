package cube.usuel;

import javax.ejb.Remote;

@Remote
public interface IUsuelEJB {

    Usuel[] getAll()
            throws Exception;

    Usuel getByVal( String id )
            throws Exception;
}
