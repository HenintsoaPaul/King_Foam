package cube.bloc;

import javax.ejb.Remote;

@Remote
public interface IBlocEJB {

    int add( Bloc bloc )
            throws Exception;

    int updatePrixRevient( Bloc bloc, double newPrix )
            throws Exception;

    Bloc[] getAll()
            throws Exception;

    Bloc[] getAllInStock()
            throws Exception;

    Bloc getById( String id )
            throws Exception;

    Bloc[] getAllMere()
            throws Exception;
}
