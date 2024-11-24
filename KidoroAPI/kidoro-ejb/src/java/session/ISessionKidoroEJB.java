package session;

import cube.bloc.Bloc;
import fabrication.AchatConsommable;
import fabrication.FormuleFabrication;
import fabrication.machine.Machine;
import fabrication.lib.PrPratiqueLib;

import javax.ejb.Remote;
import java.sql.Date;

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

    AchatConsommable[] getAchatConsommables()
            throws Exception;

    AchatConsommable[] getAchatConsommablesBeforeDaty( AchatConsommable[] achats, Date daty, String idConsommable )
            throws Exception;
}
