import cube.bloc.Bloc;
import cube.usuel.MyUsuel;
import stock.MvtStock;
import stock.MvtStockDetail;
import transformation.MyTransfo;
import transformation.Transformation;
import utilitaire.UtilDB;
import utils.ConstanteKidoro;
import utils.DateUtil;
import utils.json.JsonError;
import utils.json.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

@WebServlet( "transfos" )
public class TransfoServlet extends HeninServlet {

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
            throws IOException {
        try {
            super.setCORS( resp );
            String json = JsonUtil.getFromWeb( req );
            super.dataOk( resp );

            MyTransfo myTransfo = gson.fromJson( json, MyTransfo.class );
            System.out.println( myTransfo.constructJson() );

            Bloc blocFille = myTransfo.controller(),
                    blocMere = myTransfo.getBlockMere();

            Connection conn = null;
            Date daty = DateUtil.strToDate( myTransfo.getDaty() );
            try {
                conn = new UtilDB().GetConn();
                conn.setAutoCommit( false );

                blocMere.setDaty_sortie( daty );
                blocMere.updateToTable( conn );

                boolean misyInsertFille = true;
                double longueur = myTransfo.getLongueur(),
                        largeur = myTransfo.getLargeur(),
                        hauteur = myTransfo.getHauteur();
                if ( longueur == 0 && largeur == 0 && hauteur == 0 ) misyInsertFille = false;

                if ( misyInsertFille ) {
                    String id_bloc_base = blocMere.getId_bloc_base() == null ?
                            blocMere.getId() : blocMere.getId_bloc_base();
                    blocFille.setId_bloc_base( id_bloc_base );
                    blocFille.setId_bloc_mere( blocMere.getId() );
                    blocFille.setPrPratiqueFromVolume( blocMere.getPrixRevientVolumique() );
                    blocFille.insertToTable( conn );
                }

                Transformation t = new Transformation();
                t.setDaty( daty );
                t.setId_bloc_mere( blocMere.getId() );
                if ( misyInsertFille ) t.setId_bloc_reste( blocFille.getId() );
                t.insertToTable( conn );

                double prixRevientVolumique = blocMere.getPrixRevientVolumique();
                MvtStock mvtStock = new MvtStock();
                mvtStock.setDaty( daty );
                mvtStock.setPrix_revient_volumique( prixRevientVolumique );
                mvtStock.setId_origine( t.getId() );
                mvtStock.setId_type_mvt_stock( ConstanteKidoro.idTypeTransformation );

                mvtStock.insertToTable( conn );

                List<MyUsuel> myUsuelList = myTransfo.getUsuelACreer();
                for ( MyUsuel myUsuel : myUsuelList ) {
                    MvtStockDetail fille = MvtStockDetail.creerFromUsuel( myUsuel );

                    if ( fille != null ) {
                        fille.setId_mvt_stock( mvtStock.getId() );
                        fille.insertToTable( conn );
                    }
                }

                conn.commit();
            } catch ( Exception e ) {
                if ( conn != null ) conn.rollback();
                e.printStackTrace();
                throw e;
            } finally {
                if ( conn != null ) conn.close();
            }
        } catch ( Exception e ) {
            resp.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            resp.getWriter().write( gson.toJson( new JsonError( "Erreur interne" ) ) );

            throw new RuntimeException( e );
        }
    }

    @Override
    protected void doOptions( HttpServletRequest req, HttpServletResponse resp ) {
        setCORS( resp );
        resp.setStatus( HttpServletResponse.SC_OK );
    }
}
