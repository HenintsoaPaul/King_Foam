package utils;

import cube.bloc.IBlocEJB;
import cube.usuel.IUsuelEJB;
import etatStock.IStockEJB;
import fabrication.IAchatConsommableEJB;
import fabrication.IFormuleFabricationEJB;
import fabrication.machine.IMachineEJB;
import session.ISessionKidoroEJB;
import teta.ITetaEJB;
import fabrication.IFabricationEJB;

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

    public static IFabricationEJB getFabricationEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/FabricationEJB!fabrication.IFabricationEJB";
        return ( IFabricationEJB ) ctx.lookup( jndi );
    }

    public static IFormuleFabricationEJB getFormuleFabricationEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/FormuleFabricationEJB!fabrication.IFormuleFabricationEJB";
        return ( IFormuleFabricationEJB ) ctx.lookup( jndi );
    }

    public static IAchatConsommableEJB getAchatConsommableEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/AchatConsommableEJB!fabrication.IAchatConsommableEJB";
        return ( IAchatConsommableEJB ) ctx.lookup( jndi );
    }

    public static IStockEJB getStockEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/StockEJB!etatStock.IStockEJB";
        return ( IStockEJB ) ctx.lookup( jndi );
    }

    public static ISessionKidoroEJB getSessionKidoroEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/SessionKidoroEJB!session.ISessionKidoroEJB";
        return ( ISessionKidoroEJB ) ctx.lookup( jndi );
    }

    public static IMachineEJB getMachineEJB()
            throws NamingException {
        Context ctx = new InitialContext();
        String jndi = "java:global/kidoro/MachineEJB!fabrication.machine.IMachineEJB";
        return ( IMachineEJB ) ctx.lookup( jndi );
    }
}
