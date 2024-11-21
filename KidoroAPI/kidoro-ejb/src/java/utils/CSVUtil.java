package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import cube.bloc.Bloc;
import fabrication.AchatConsommable;
import utilitaire.UtilDB;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil<T> {
    public static List<AchatConsommable> loadAchatConsommable( String filePath ) {
        List<AchatConsommable> arr = new ArrayList<>();
        try ( CSVReader reader = new CSVReaderBuilder( new FileReader( filePath ) )
                .withSkipLines( 1 ) // Skip header row
                .build() ) {
            String[] nextLine;
            while ( ( nextLine = reader.readNext() ) != null ) {
                String[] rowCsv = new String[ 5 ];
                for ( int i = 0; i < nextLine.length; i++ ) {
                    rowCsv[ i ] = nextLine[ i ];
                }
                arr.add( new AchatConsommable( rowCsv ) );
            }
        } catch ( Exception e ) {
            throw new RuntimeException( "Error reading CSV file", e );
        }
        return arr;
    }

    public static void insertBlocs( String filePath )
            throws SQLException {
        int total = 0;
        Connection conn = null;
        try ( CSVReader reader = new CSVReaderBuilder( new FileReader( filePath ) )
                .withSkipLines( 1 ) // Skip header row
                .build() ) {

            conn = new UtilDB().GetConn();
            conn.setAutoCommit( false );

            String sqlInsert = "INSERT INTO bloc (daty_entree, longueur, largeur, hauteur, PRIX_REVIENT_PRATIQUE, id_machine, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement( sqlInsert );

            String[] nextLine;
            int batchSize = 20000;
            int anatyBatch = 0;

            Bloc b = new Bloc();

            while ( ( nextLine = reader.readNext() ) != null ) {
                String dt = DateUtil.formatDate( nextLine[ 0 ] );
                pstmt.setDate( 1, DateUtil.strToDate( dt ) );      // daty_entree

                pstmt.setDouble( 2, Double.parseDouble( nextLine[ 1 ] ) );   // longueur
                pstmt.setDouble( 3, Double.parseDouble( nextLine[ 2 ] ) );   // largeur
                pstmt.setDouble( 4, Double.parseDouble( nextLine[ 3 ] ) );   // hauteur

                pstmt.setDouble( 5, Double.parseDouble( nextLine[ 4 ] ) );   // prPratique

                pstmt.setString( 6, nextLine[ 5 ] );   // id_machine

                b.construirePK( conn );
                pstmt.setString( 7, b.getId() );

                pstmt.addBatch();
                anatyBatch++;

                if ( anatyBatch >= batchSize ) {
                    total += batchSize;
                    System.out.println( "nanao insert " + anatyBatch + " | total = " + total );
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    anatyBatch = 0;
                }
            }
            if ( anatyBatch > 0 ) {
                total += anatyBatch;
                System.out.println( "nanao insert " + anatyBatch + " | total = " + total );
                pstmt.executeBatch();
                pstmt.clearBatch();
            }
            conn.commit();
        } catch ( Exception e ) {
            if ( conn != null ) conn.rollback();
            throw new RuntimeException( "Error reading CSV file", e );
        } finally {
            if ( conn != null ) conn.close();
        }
    }
}
