package admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jdesktop.beansbinding.Converter;

public class DateConverter extends Converter<Date, String> {

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Override
    public String convertForward(Date value) {
        return dateFormat.format(value);
    }

    @Override
    public Date convertReverse(String value) {
        try {
            return dateFormat.parse(value);
        } catch (java.text.ParseException ex) {
            return Calendar.getInstance().getTime();
        }

    }

}
