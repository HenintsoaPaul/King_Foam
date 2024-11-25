package session;

import cube.bloc.Bloc;
import fabrication.AchatConsommable;
import fabrication.FormuleFabrication;
import fabrication.machine.Machine;
import fabrication.lib.PrPratiqueLib;
import holiday.Holiday;

import javax.ejb.Remote;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.List;

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

    List<Holiday> getHolidays()
            throws NamingException, FileNotFoundException;
}
