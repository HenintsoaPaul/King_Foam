package utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateUtil {

    /**
     * Transformer un string dans le format "yyyy-MM-dd" vers un java.sql.Date
     */
    public static Date strToDate( String daty )
            throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat( "yyyy-MM-dd" );
        java.util.Date util = sd.parse( daty );
        return new Date( util.getTime() );
    }

    public static String formatDate( String dateString ) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate date = LocalDate.parse( dateString, inputFormatter );
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
        return date.format( outputFormatter );
    }
}
