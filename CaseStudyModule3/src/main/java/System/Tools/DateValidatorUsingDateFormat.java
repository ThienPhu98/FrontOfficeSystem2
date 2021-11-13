package System.Tools;

import java.text.*;

public class DateValidatorUsingDateFormat{

    public boolean isDateValid(String date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
