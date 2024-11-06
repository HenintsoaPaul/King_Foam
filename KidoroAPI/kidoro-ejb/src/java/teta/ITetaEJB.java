package teta;

import javax.ejb.Remote;

@Remote
public interface ITetaEJB {

    Teta get()
            throws Exception;
}
