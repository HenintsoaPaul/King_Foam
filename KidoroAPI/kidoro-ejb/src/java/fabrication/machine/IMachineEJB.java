package fabrication.machine;

import javax.ejb.Remote;

@Remote
public interface IMachineEJB {

    Machine[] getAll()
            throws Exception;
}
