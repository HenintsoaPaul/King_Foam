package utils;

import holiday.Holiday;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public static boolean isHoliday( List<Holiday> holidays, Date date ) {
        for ( Holiday h : holidays ) {
            if ( h.getDate().equals( date.toString() ) ) return true;
        }
        return false;
    }

    public static boolean isWeekend( Date date ) {
        int noDay = date.getDay();
        return noDay == 0 || noDay == 6;
    }

    public static boolean isNotJrOuvrable( List<Holiday> holidayList, Date date ) {
        return isHoliday( holidayList, date ) || isWeekend( date );
    }
}
