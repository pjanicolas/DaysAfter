package com.example.daysafter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GodClass {

    public static final int DAYS = 0;
    public static final int MONTHS = 1;
    public static final int YEAR = 2;

    /**
     *
     * @param dateCurrentDate
     * @param dateTargetDate
     * @return
     */
    public String generateDayAndMonthDifference(Date dateCurrentDate, Date dateTargetDate)
    {
        Long longDaysBetween = getDaysBetween(dateCurrentDate, dateTargetDate);
        int longMonthsBetween = (int)Math.floor(longDaysBetween / 30);
        String sMonthsMessage = (longMonthsBetween > 1) ? " months" : " month";
        return longDaysBetween + " days, " + longMonthsBetween  + sMonthsMessage;
    }

    /**
     * @param sDate
     * @return
     * @throws ParseException
     */
    public Date convertStringToDate(String sDate) throws ParseException {
        SimpleDateFormat sdfFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return sdfFormatter.parse(sDate);
    }

    public String getValueInString(String sDate, int iType)
    {
       String[] splittedString = sDate.split("/");
       return splittedString[iType];
    }

    /**
     *
     * @param objDate
     * @return
     */
    private String convertDateToString(Date objDate)
    {
        DateFormat dfFormatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.US);
        return dfFormatter.format(objDate);
    }

    /**
     * dateEndDate
     * @param dateEndDate
     * @param dateStartDate
     * @return
     */
    private Long getDaysBetween(Date dateEndDate, Date dateStartDate)
    {
        long longDayDifferences = dateEndDate.getTime() - dateStartDate.getTime();
        return TimeUnit.DAYS.convert(longDayDifferences, TimeUnit.MILLISECONDS);
    }
}
