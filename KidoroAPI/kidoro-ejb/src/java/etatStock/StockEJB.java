package etatStock;

import bean.CGenUtil;

import javax.ejb.Stateless;

@Stateless
public class StockEJB implements IStockEJB {

    @Override
    public StockUsuel[] getStockUsuel()
            throws Exception {
        return ( StockUsuel[] ) CGenUtil.rechercher( new StockUsuel(), null, null, "" );
    }
}
