package session;

import cube.bloc.IBlocEJB;
import fabrication.FormuleFabrication;
import fabrication.IFormuleFabricationEJB;
import fabrication.machine.IMachineEJB;
import fabrication.machine.Machine;
import utils.EJBGetter;
import utils.RandomUtil;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.NamingException;
import java.io.Serializable;

@Stateful
@AccessTimeout( 10000L )
@TransactionAttribute( TransactionAttributeType.SUPPORTS )
public class SessionKidoroEJB implements ISessionKidoroEJB, Serializable {

    FormuleFabrication[] formulesFabrication;
    Machine[] machines;
    double moyennePrPratiqueVolumique = -1;

    // Getters n Setters
    @Override
    public FormuleFabrication[] getFormulesFabrication()
            throws Exception {
        if ( this.formulesFabrication == null ) {
            IFormuleFabricationEJB ejb = EJBGetter.getFormuleFabricationEJB();
            this.setFormulesFabrication( ejb.getFormulesFabrication() );
        }
        return this.formulesFabrication;
    }

    public void setFormulesFabrication( FormuleFabrication[] formulesFabrication ) {
        this.formulesFabrication = formulesFabrication;
    }

    public Machine[] getMachines()
            throws Exception {
        if ( machines == null ) {
            IMachineEJB ejb = EJBGetter.getMachineEJB();
            this.setMachines( ejb.getAll() );
        }
        return machines;
    }

    public void setMachines( Machine[] machines ) {
        this.machines = machines;
    }

    @Override
    public Machine getRandomMachine()
            throws Exception {
        Machine[] ms = getMachines();
        int index = RandomUtil.getRandomNumber( 0, ms.length );
        return ms[ index ];
    }

    @Override
    public double getMoyennePrPratiqueVolumique()
            throws NamingException {
        if ( moyennePrPratiqueVolumique == -1 ) {
            IBlocEJB ejb = EJBGetter.getBlocEJB();
        }
        return moyennePrPratiqueVolumique;
    }
}
