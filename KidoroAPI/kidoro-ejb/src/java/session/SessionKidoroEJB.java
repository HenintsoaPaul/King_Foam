package session;

import fabrication.FormuleFabrication;
import fabrication.IFormuleFabricationEJB;
import utils.EJBGetter;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateful
@AccessTimeout( 10000L )
@TransactionAttribute( TransactionAttributeType.SUPPORTS )
public class SessionKidoroEJB implements ISessionKidoroEJB {

    FormuleFabrication[] formulesFabrication;

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
}
