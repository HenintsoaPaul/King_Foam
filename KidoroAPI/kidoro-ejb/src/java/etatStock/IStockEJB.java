package etatStock;

import javax.ejb.Remote;

@Remote
public interface IStockEJB {

    StockUsuel[] getStockUsuel()
            throws Exception;

    StockBlocOptim[] getStockBlocOptim()
            throws Exception;

    StockBlocMin[] getStockBlocMin()
            throws Exception;
}
