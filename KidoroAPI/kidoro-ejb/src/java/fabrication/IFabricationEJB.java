package fabrication;

import session.ISessionKidoroEJB;

import javax.ejb.Remote;

@Remote
public interface IFabricationEJB {

    void doFabrications( int nbFabrication, ISessionKidoroEJB session )
            throws Exception;
}
