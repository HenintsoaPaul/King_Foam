package session;

import fabrication.FormuleFabrication;
import fabrication.machine.Machine;

import javax.ejb.Remote;
import javax.naming.NamingException;

@Remote
public interface ISessionKidoroEJB {

    FormuleFabrication[] getFormulesFabrication()
            throws Exception;

    Machine getRandomMachine()
            throws Exception;

    double getMoyennePrPratiqueVolumique()
            throws NamingException;
}
