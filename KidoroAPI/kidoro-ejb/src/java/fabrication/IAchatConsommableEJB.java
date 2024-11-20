package fabrication;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IAchatConsommableEJB {

    List<AchatConsommable> loadFromCsv( String filePath );

    void saveAll( List<AchatConsommable> achats )
            throws Exception;
}
