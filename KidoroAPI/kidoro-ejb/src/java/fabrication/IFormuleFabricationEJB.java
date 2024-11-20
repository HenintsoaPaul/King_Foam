package fabrication;

import javax.ejb.Remote;

@Remote
public interface IFormuleFabricationEJB {

    FormuleFabrication[] getFormulesFabrication()
            throws Exception;
}
