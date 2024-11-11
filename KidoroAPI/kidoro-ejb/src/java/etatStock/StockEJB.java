package etatStock;

import bean.CGenUtil;
import stock.MvtStockDetailLib;

import javax.ejb.Stateless;

@Stateless
public class StockEJB implements IStockEJB {

    @Override
    public StockUsuel[] getStockUsuel()
            throws Exception {
        return ( StockUsuel[] ) CGenUtil.rechercher( new StockUsuel(), null, null, "" );
    }

    @Override
    public StockBlocOptim[] getStockBlocOptim()
            throws Exception {
        return ( StockBlocOptim[] ) CGenUtil.rechercher( new StockBlocOptim(), null, null, "" );
    }

    @Override
    public StockBlocMin[] getStockBlocMin()
            throws Exception {
        return ( StockBlocMin[] ) CGenUtil.rechercher( new StockBlocMin(), null, null, "" );
    }

    @Override
    public MvtStockDetailLib[] getAllUsuelLib()
            throws Exception {
        return ( MvtStockDetailLib[] ) CGenUtil.rechercher( new MvtStockDetailLib(), null, null, "" );
    }
}
