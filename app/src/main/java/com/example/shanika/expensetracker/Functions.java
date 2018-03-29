package com.example.shanika.expensetracker;

import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Shanika on 2/24/2018.
 */

public class Functions {

    public String date;

    public String addDate() {

        final Calendar c = Calendar.getInstance();
        String mYear = Integer.toString(c.get(Calendar.YEAR));
        String mMonth = Integer.toString(c.get(Calendar.MONTH)+1);
        String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mn ="";
        String dy ="";

        if(String.valueOf(mDay).length()==1){
            dy ="0";
        }

        if(String.valueOf(mMonth).length()==1){
            mn ="0";
        }


        date = "" + mYear + "-" +mn+ mMonth + "-" +dy+ mDay + "";
        return date;
    }

    public String dateOfCal(Calendar c){
        String mYear = Integer.toString(c.get(Calendar.YEAR));
        String mMonth = Integer.toString(c.get(Calendar.MONTH)+1);
        String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mn ="";
        String dy ="";

        if(String.valueOf(mDay).length()==1){
            dy ="0";
        }

        if(String.valueOf(mMonth).length()==1){
            mn ="0";
        }


        date = "" + mYear + "-" +mn+ mMonth + "-" +dy+ mDay + "";
        return date;
    }

    public ArrayList<String> getDateRange(){
        String beginning, end;

        ArrayList<String> dates = new ArrayList<>();

        {
            Calendar calendar =getCalenderForNow();
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            beginning=dateOfCal(calendar);
            dates.add(beginning);
        }

        {
            Calendar calendar =getCalenderForNow();
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end= dateOfCal(calendar);
            dates.add(end);
        }


        return dates;
    }

    private static Calendar getCalenderForNow(){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        return cal;
    }

}
