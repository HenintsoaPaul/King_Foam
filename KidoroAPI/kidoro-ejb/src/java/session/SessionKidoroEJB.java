package session;

import fabrication.FormuleFabrication;
import fabrication.FormuleFabricationEJB;
import utils.EJBGetter;

import javax.ejb.Stateful;

@Stateful
public class SessionKidoroEJB {

    FormuleFabrication[] formulesFabrication;

    FormuleFabrication[] getFormulesFabrication()
            throws Exception {
        if ( this.formulesFabrication == null ) {
            FormuleFabricationEJB ejb = ( FormuleFabricationEJB ) EJBGetter.getFormuleFabricationEJB();
            this.formulesFabrication = ejb.getFormulesFabrication();
        }
        return this.formulesFabrication;
    }
}
