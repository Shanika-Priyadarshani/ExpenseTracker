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
        String mMonth = Integer.toString(c.get(Calendar.MONTH));
        String mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));

        date = "" + mDay + "/" + mMonth + "/" + mYear + "";
        return date;
    }


}
