package com.example.shanika.expensetracker;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Shanika on 2/24/2018.
 */

public class Functions {

    public String date;

    private static Calendar getCalenderForNow() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        return cal;
    }

    public String addDate() {

        final Calendar c = Calendar.getInstance();
        return dateInFormat(c);
    }

    public String dateInFormat(Calendar c) {
        String mYear = Integer.toString(c.get(Calendar.YEAR));
        String mMonth = Integer.toString(c.get(Calendar.MONTH) + 1);
        String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mn = "";
        String dy = "";

        if (String.valueOf(mDay).length() == 1) {
            dy = "0";
        }

        if (String.valueOf(mMonth).length() == 1) {
            mn = "0";
        }


        String formattedDate = "" + mYear + "-" + mn + mMonth + "-" + dy + mDay + "";
        return formattedDate;
    }

    public String dateOfCal(Calendar c) {
        String mYear = Integer.toString(c.get(Calendar.YEAR));
        String mMonth = Integer.toString(c.get(Calendar.MONTH) + 1);
        String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mn = "";
        String dy = "";

        if (String.valueOf(mDay).length() == 1) {
            dy = "0";
        }

        if (String.valueOf(mMonth).length() == 1) {
            mn = "0";
        }


        date = "" + mYear + "-" + mn + mMonth + "-" + dy + mDay + "";
        return date;
    }

    public ArrayList<String> getDateRange() {
        String beginning, end;

        ArrayList<String> dates = new ArrayList<>();

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            beginning = dateOfCal(calendar);
            dates.add(beginning);
        }

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = dateOfCal(calendar);
            dates.add(end);
        }


        return dates;
    }

    public String getCurrentMonth() {
        String month = "";
        Calendar calendar = getCalenderForNow();
        int m = calendar.get(Calendar.MONTH);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (m >= 0 && m <= 11) {
            month = months[m];
        }
        return month;
    }

    public ArrayList<String> previousMonthDates() {
        ArrayList<String> dates = new ArrayList<>();
        String begin, end;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        begin = dateInFormat(cal);
        dates.add(begin);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        end = dateInFormat(cal);
        dates.add(end);
        return dates;
    }

    public String getToday() {
        Calendar calendar = getCalenderForNow();
        return dateOfCal(calendar);
    }

    public ArrayList<String> getCurrentWeek() {
        String beginning, end;

        ArrayList<String> dates = new ArrayList<>();

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
            beginning = dateOfCal(calendar);
            dates.add(beginning);
        }

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
            end = dateOfCal(calendar);
            dates.add(end);
        }

        return dates;

    }

    public ArrayList<String> getCurrentYear() {
        String beginning, end;

        ArrayList<String> dates = new ArrayList<>();

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
            beginning = dateOfCal(calendar);
            dates.add(beginning);
        }

        {
            Calendar calendar = getCalenderForNow();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
            end = dateOfCal(calendar);
            dates.add(end);
        }

        return dates;

    }
}
