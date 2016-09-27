package es.alexcortinas.oai.dataprovider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.alexcortinas.oai.dataprovider.model.constants.Granularity;

public class DateUtils {

    public static Calendar stringToCalendar(String date, Granularity granularity) throws ParseException {

        if (StringUtils.isBlank(date)) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (granularity != null) {
            if (granularity == Granularity.SECONDS) {
                if (date.length() > 10) {
                    formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                }
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(formatter.parse(date));

        return cal;
    }

    public static String calendarToString(Calendar date, Granularity granularity) {

        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (granularity != null) {
            if (granularity == Granularity.SECONDS) {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
        }

        String formattedDate = formatter.format(date.getTime());
        return formattedDate;
    }

}
