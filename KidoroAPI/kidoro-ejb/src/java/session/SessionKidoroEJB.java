package session;

import bean.CGenUtil;
import fabrication.FormuleFabrication;
import fabrication.IFormuleFabricationEJB;
import fabrication.machine.IMachineEJB;
import fabrication.machine.Machine;
import lib.PrPratiqueLib;
import utils.EJBGetter;
import utils.RandomUtil;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.Serializable;

@Stateful
@AccessTimeout( 10000L )
@TransactionAttribute( TransactionAttributeType.SUPPORTS )
public class SessionKidoroEJB implements ISessionKidoroEJB, Serializable {

    FormuleFabrication[] formulesFabrication;
    Machine[] machines;
    PrPratiqueLib prPratiqueLib;
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

    private void setFormulesFabrication( FormuleFabrication[] formulesFabrication ) {
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

    private void setMachines( Machine[] machines ) {
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
    public PrPratiqueLib getPrPratiqueLib()
            throws Exception {
        if ( prPratiqueLib == null ) {
            PrPratiqueLib pr = ( PrPratiqueLib ) CGenUtil.rechercher( new PrPratiqueLib(), null, null, "" )[ 0 ];
            this.setPrPratiqueLib( pr );
        }
        return prPratiqueLib;
    }

    private void setPrPratiqueLib( PrPratiqueLib prPratiqueLib ) {
        this.prPratiqueLib = prPratiqueLib;
    }

    @Override
    public double getMoyennePrPratiqueVolumique()
            throws Exception {
        if ( moyennePrPratiqueVolumique == -1 ) {
            PrPratiqueLib pr = getPrPratiqueLib();
            this.setMoyennePrPratiqueVolumique( pr.getPr_pratique_volumique() );
        }
        return moyennePrPratiqueVolumique;
    }

    private void setMoyennePrPratiqueVolumique( double v ) {
        this.moyennePrPratiqueVolumique = v;
    }
}
