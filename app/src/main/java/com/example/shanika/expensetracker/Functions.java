package com.example.shanika.expensetracker;

import android.graphics.Typeface;
import android.widget.TextView;

import java.util.Calendar;

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
}
