package com.example.shanika.expensetracker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static com.example.shanika.expensetracker.R.array.day_array;

public class AddFrequentRecords extends AppCompatActivity {

    private ImageButton back;
    private DatePickerDialog datePickerDialog;
    private Context context;
    private Spinner spinner;
    private ImageButton occTime;
    private Spinner repeatSpinner;
    private TextView occuringTime;
    private String half;
    private int mhour;
    private int mminute;
    private Spinner occSpinner;

    // creating for the first time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frequent_records);
        onClickBack();
        onRepeatItemSelected();
        occuringTimeBtnPressed();

        final TextView startDateView = (TextView) findViewById(R.id.startDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        startDateView.setText(date);
        ImageButton startDateBtn = (ImageButton) findViewById(R.id.startDateBtn);
        startDateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(AddFrequentRecords.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startDateView.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            }
        });

        context = getApplicationContext();

        ArrayList<String> ary = new Schema(context).getMenuList("");
        spinner = (Spinner) findViewById(R.id.AllCategorySet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ary);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    //what happens on back button click
    public void onClickBack() {

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }

        );
    }

    // System back key press handling
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddFrequentRecords.this, FrequentRecordDisplay.class);
        startActivity(intent);
        finish();
    }

    public void occuringTimeBtnPressed() {
        repeatSpinner = (Spinner) findViewById(R.id.repeatSpinner);
        occTime = (ImageButton) findViewById(R.id.occuringTimeBtn);
        occuringTime = (TextView) findViewById(R.id.occuringTime);
        occTime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String repeatingPattern = repeatSpinner.getSelectedItem().toString();

                        if (repeatingPattern.equals("Daily") ) {

                           final Calendar c = Calendar.getInstance();
                             mhour = c.get(Calendar.HOUR_OF_DAY);
                            mminute = c.get(Calendar.MINUTE);

                            TimePickerDialog timePickerDialog = new TimePickerDialog(AddFrequentRecords.this,
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(TimePicker view, int hour,
                                                              int minute) {
                                            half ="A.M.";
                                            if (hour==0){
                                                hour=12;
                                            }
                                            else if (hour==12){
                                                half="P.M.";

                                            }

                                            else if (hour>12){
                                                hour=hour-12;
                                                half="P.M.";

                                            }

                                            String min ="";
                                            String hr ="";

                                            if(String.valueOf(hour).length()==1){
                                                hr ="0";
                                            }

                                            if(String.valueOf(minute).length()==1){
                                                min ="0";
                                            }

                                            occuringTime.setText(" "+hr+hour + " : " +min+ minute+" "+half);
                                        }
                                    }, mhour, mminute, false);
                            timePickerDialog.show();
                        }


                        else  if (repeatingPattern.equals("Weekly") ) {
                            //occSpinner = (Spinner)findViewById(R.id.occSpinner);

                        }


                        else  if (repeatingPattern.equals("Monthly") ) {


                        }



                        else  if (repeatingPattern.equals("Yearly") ) {
                        /*   final Calendar c = Calendar.getInstance();

                            c.set(Calendar.YEAR,2018);
                            c.set(Calendar.DAY_OF_YEAR,1);
                            Date start = c.getTime();

                            c.set(Calendar.YEAR,2018);
                            c.set(Calendar.MONTH,11);
                            c.set(Calendar.DAY_OF_MONTH,31);
                            Date end = c.getTime();

                            int mYear = c.get(Calendar.YEAR);
                            int mMonth = c.get(Calendar.MONTH);
                            int mDay = c.get(Calendar.DAY_OF_MONTH);



                        DatePickerDialog datePickDialog = new DatePickerDialog(AddFrequentRecords.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        occuringTime.setText(dayOfMonth + "/"
                                                + (monthOfYear + 1));
                                    }
                                }, mYear, mMonth, mDay);

                        datePickDialog.getDatePicker().setMinDate(start.getTime());
                        datePickDialog.getDatePicker().setMaxDate(end.getTime());
                        datePickDialog.show();
                            */
                    }
                    }
                }

        );

    }

    public void onRepeatItemSelected(){
     final Spinner repeatSpinner= (Spinner)findViewById(R.id.repeatSpinner);
        repeatSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    LinearLayout dayilyLayout =(LinearLayout)findViewById(R.id.dayilyLayout);
                    TextView occuringTime =(TextView)findViewById(R.id.occuringTime);
                    ImageButton occuringTimeBtn =(ImageButton)findViewById(R.id.occuringTimeBtn);

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String repeatItem= repeatSpinner.getSelectedItem().toString();
                        LinearLayout yearLayout =(LinearLayout)findViewById(R.id.yearLayout);
                        Spinner weekDaysSpinner =(Spinner)findViewById(R.id.weekDaysSpinner);
                        Spinner monthDaysSpinner =(Spinner)findViewById(R.id.monthDaysSpinner);
                        Spinner yrMonth =(Spinner)findViewById(R.id.yrMonth);
                        Spinner yrDay =(Spinner)findViewById(R.id.yrDay);



                        if (repeatItem.equals("Daily") ) {

                            yearLayout.setVisibility(View.INVISIBLE);
                            weekDaysSpinner.setVisibility(View.INVISIBLE);
                            monthDaysSpinner.setVisibility(View.INVISIBLE);
                            yrMonth.setVisibility(View.INVISIBLE);
                            yrDay.setVisibility(View.INVISIBLE);
                            dayilyLayout.setVisibility(View.VISIBLE);
                            occuringTime.setVisibility(View.VISIBLE);
                            occuringTimeBtn.setVisibility(View.VISIBLE);
                        }

                        else if (repeatItem.equals("Weekly") ) {
                            dayilyLayout.setVisibility(View.INVISIBLE);
                            occuringTime.setVisibility(View.INVISIBLE);
                            occuringTimeBtn.setVisibility(View.INVISIBLE);
                            yearLayout.setVisibility(View.INVISIBLE);
                            monthDaysSpinner.setVisibility(View.INVISIBLE);
                            yrMonth.setVisibility(View.INVISIBLE);
                            yrDay.setVisibility(View.INVISIBLE);
                            weekDaysSpinner.setVisibility(View.VISIBLE);
                        }

                        else if (repeatItem.equals("Monthly") ) {
                            dayilyLayout.setVisibility(View.INVISIBLE);
                            occuringTime.setVisibility(View.INVISIBLE);
                            occuringTimeBtn.setVisibility(View.INVISIBLE);
                            yearLayout.setVisibility(View.INVISIBLE);
                            weekDaysSpinner.setVisibility(View.INVISIBLE);
                            yrMonth.setVisibility(View.INVISIBLE);
                            yrDay.setVisibility(View.INVISIBLE);
                            monthDaysSpinner.setVisibility(View.VISIBLE);
                        }

                        else if (repeatItem.equals("Yearly") ) {
                            dayilyLayout.setVisibility(View.INVISIBLE);
                            occuringTime.setVisibility(View.INVISIBLE);
                            occuringTimeBtn.setVisibility(View.INVISIBLE);
                            weekDaysSpinner.setVisibility(View.INVISIBLE);
                            monthDaysSpinner.setVisibility(View.INVISIBLE);
                            yearLayout.setVisibility(View.VISIBLE);
                            yrMonth.setVisibility(View.VISIBLE);
                            yrDay.setVisibility(View.VISIBLE);
                        }

                        }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        dayilyLayout.setVisibility(View.VISIBLE);
                        occuringTime.setVisibility(View.VISIBLE);
                        occuringTimeBtn.setVisibility(View.VISIBLE);
                    }
                }

        );

    }
}
