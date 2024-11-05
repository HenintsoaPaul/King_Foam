package cube.bloc;

import javax.ejb.Remote;

@Remote
public interface IBlocEJB {

    int add( Bloc bloc )
            throws Exception;
}
