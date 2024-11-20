package fabrication;

import bean.CGenUtil;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;

@Stateless
public class FormuleFabricationEJB implements IFormuleFabricationEJB {

    @Override
    public FormuleFabrication[] getFormulesFabrication()
            throws Exception {
        Connection conn = new UtilDB().GetConn();
        return ( FormuleFabrication[] ) CGenUtil.rechercher( new FormuleFabrication(), null, null, conn, "" );
    }
}
