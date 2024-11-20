package fabrication;

import javax.ejb.Remote;

@Remote
public interface IFabricationEJB {

    void doFabrications( int nbFabrication, FormuleFabrication[] formuleFabrications )
            throws Exception;
}
