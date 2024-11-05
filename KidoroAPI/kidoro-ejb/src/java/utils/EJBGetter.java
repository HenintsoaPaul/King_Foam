package utils;

import cube.bloc.IBlocEJB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class EJBGetter {

    public static IBlocEJB getBlocEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/BlocEJB!cube.bloc.IBlocEJB";
        return ( IBlocEJB ) ctx.lookup( jndi );
    }
}
