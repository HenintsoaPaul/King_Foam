package holiday;

import javax.ejb.Remote;
import java.io.FileNotFoundException;
import java.util.List;

@Remote
public interface IHolidayEJB {

    List<Holiday> getAll()
            throws FileNotFoundException;
}
