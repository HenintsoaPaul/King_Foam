package transformation;

import javax.ejb.Remote;

@Remote
public interface ITransfoEJB {

    int add(Transformation transformation)
            throws Exception;
}
