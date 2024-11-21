package session;

import cube.bloc.Bloc;
import fabrication.FormuleFabrication;
import fabrication.machine.Machine;
import fabrication.lib.PrPratiqueLib;

import javax.ejb.Remote;

@Remote
public interface ISessionKidoroEJB {

    FormuleFabrication[] getFormulesFabrication()
            throws Exception;

    Machine getRandomMachine()
            throws Exception;

    PrPratiqueLib getPrPratiqueLib()
            throws Exception;

    double getMoyennePrPratiqueVolumique()
            throws Exception;

    Bloc[] getBlocs()
            throws Exception;
}
