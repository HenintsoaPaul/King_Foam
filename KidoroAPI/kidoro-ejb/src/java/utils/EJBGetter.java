package utils;

import cube.bloc.IBlocEJB;
import cube.usuel.IUsuelEJB;
import etatStock.IStockEJB;
import teta.ITetaEJB;
import transformation.ITransfoEJB;

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

    public static IUsuelEJB getUsuelEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/UsuelEJB!cube.usuel.IUsuelEJB";
        return ( IUsuelEJB ) ctx.lookup( jndi );
    }

    public static ITetaEJB getTetaEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/TetaEJB!teta.ITetaEJB";
        return ( ITetaEJB ) ctx.lookup( jndi );
    }

    public static ITransfoEJB getTransfoEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/TransfoEJB!transformation.ITransfoEJB";
        return ( ITransfoEJB ) ctx.lookup( jndi );
    }

    public static IStockEJB getStockEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/StockEJB!etatStock.IStockEJB";
        return ( IStockEJB ) ctx.lookup( jndi );
    }
}
