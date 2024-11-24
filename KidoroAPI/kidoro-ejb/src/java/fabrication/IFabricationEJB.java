package fabrication;

import session.ISessionKidoroEJB;

import javax.ejb.Remote;

@Remote
public interface IFabricationEJB {

    void doUpdateFabrications( ISessionKidoroEJB session )
            throws Exception;
}
