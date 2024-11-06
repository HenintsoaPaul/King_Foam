package teta;

import bean.CGenUtil;

import javax.ejb.Stateless;

@Stateless
public class TetaEJB implements ITetaEJB {

    @Override
    public Teta get()
            throws Exception {
        String apresWhere = " order by id desc";
        return ( Teta ) CGenUtil.rechercher( new Teta(), null, null, apresWhere )[0];
    }
}
