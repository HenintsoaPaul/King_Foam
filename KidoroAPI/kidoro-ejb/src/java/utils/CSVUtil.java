package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fabrication.AchatConsommable;
import fabrication.Consommable;

import java.io.FileReader;
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

    public static List<Consommable> loadConsommable( String filePath ) {
        List<Consommable> arr = new ArrayList<>();
        try ( CSVReader reader = new CSVReaderBuilder( new FileReader( filePath ) )
                .withSkipLines( 1 ) // Skip header row
                .build() ) {
            String[] nextLine;
            while ( ( nextLine = reader.readNext() ) != null ) {
                String[] rowCsv = new String[ 5 ];
                for ( int i = 0; i < nextLine.length; i++ ) {
                    rowCsv[ i ] = nextLine[ i ];
                }
                arr.add( new Consommable( rowCsv ) );
            }
        } catch ( Exception e ) {
            throw new RuntimeException( "Error reading CSV file", e );
        }
        return arr;
    }
}
