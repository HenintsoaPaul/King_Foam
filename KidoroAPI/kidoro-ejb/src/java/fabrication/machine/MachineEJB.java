package fabrication.machine;

import bean.CGenUtil;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;

@Stateless
public class MachineEJB implements IMachineEJB {

    @Override
    public Machine[] getAll()
            throws Exception {
        return ( Machine[] ) CGenUtil.rechercher( new Machine(), null, null, "" );
    }
}
