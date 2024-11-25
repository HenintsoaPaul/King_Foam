package session;

import bean.CGenUtil;
import cube.bloc.Bloc;
import fabrication.AchatConsommable;
import fabrication.FormuleFabrication;
import fabrication.IFormuleFabricationEJB;
import fabrication.machine.IMachineEJB;
import fabrication.machine.Machine;
import fabrication.lib.PrPratiqueLib;
import holiday.Holiday;
import utils.EJBGetter;
import utils.random.RandomIntUtil;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Stateful
@AccessTimeout( 100000000000L )
@TransactionAttribute( TransactionAttributeType.SUPPORTS )
public class SessionKidoroEJB implements ISessionKidoroEJB, Serializable {

    FormuleFabrication[] formulesFabrication;
    Machine[] machines;
    PrPratiqueLib prPratiqueLib;
    double moyennePrPratiqueVolumique = -1;
    Bloc[] blocs;
    AchatConsommable[] achatConsommables;
    List<Holiday> holidays;

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
        int index = RandomIntUtil.getRandInt( 0, ms.length );
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
            this.setMoyennePrPratiqueVolumique( pr.getAvg_pr_pratique_volumique() );
        }
        return moyennePrPratiqueVolumique;
    }

    @Override
    public Bloc[] getBlocs()
            throws Exception {
        if ( blocs == null ) {
            this.setBlocs();
        }
        return this.blocs;
    }

    @Override
    public void setBlocsFrom( Bloc[] blocs ) {
        this.blocs = blocs;
    }

    @Override
    public AchatConsommable[] getAchatConsommables()
            throws Exception {
        if ( this.achatConsommables == null ) {
            this.setAchatConsommables();
        }
        return this.achatConsommables;
    }

    @Override
    public AchatConsommable[] getAchatConsommablesBeforeDaty( AchatConsommable[] achats, Date daty, String idConsommable ) {
        return Arrays.stream( achats )
                .filter( achat -> achat.getDaty().compareTo( daty ) <= 0
                        && achat.getReste() > 0
                        && achat.getId_consommable().equals( idConsommable ) )
                .sorted( Comparator.comparing( AchatConsommable::getDaty ) ).toArray( AchatConsommable[]::new );
    }

    @Override
    public List<Holiday> getHolidays()
            throws NamingException, FileNotFoundException {
        if ( holidays == null ) {
            holidays = EJBGetter.getHolidayEJB().getAll();
        }
        return holidays;
    }

    private void setAchatConsommables()
            throws Exception {
        AchatConsommable[] arr = ( AchatConsommable[] ) CGenUtil.rechercher( new AchatConsommable(), null, null, "" );
        this.achatConsommables = arr;
    }

    public void setBlocs()
            throws Exception {
        String apresWhere = " order by daty_entree";
        this.blocs = ( Bloc[] ) CGenUtil.rechercher( new Bloc(), null, null, apresWhere );
        System.out.println( "Azo ny blocs rehetra (len: " + this.blocs.length + ")" );
    }

    private void setMoyennePrPratiqueVolumique( double v ) {
        this.moyennePrPratiqueVolumique = v;
    }
}
