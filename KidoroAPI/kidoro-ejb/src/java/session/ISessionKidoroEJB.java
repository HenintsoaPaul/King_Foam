package session;

import fabrication.FormuleFabrication;

import javax.ejb.Remote;

@Remote
public interface ISessionKidoroEJB {

    FormuleFabrication[] getFormulesFabrication()
            throws Exception;
}
