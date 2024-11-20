package fabrication;

import javax.ejb.Remote;
import java.sql.Date;

@Remote
public interface IFabricationEJB {

    int add( Fabrication fabrication )
            throws Exception;

    double getPrixRevientPratiqueVolumique( Date daty )
            throws Exception;
}
