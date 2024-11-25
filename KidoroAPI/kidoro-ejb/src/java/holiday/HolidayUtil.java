package holiday;

import java.sql.Date;
import java.util.List;

public abstract class HolidayUtil {
    private List<Date> holidays;

    public static boolean isHoliday( Date date ) {
        return false;
    }
}
