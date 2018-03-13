package com.example.shanika.expensetracker;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Search extends AppCompatActivity {
    private static ImageButton back;
    private TextView startDateView;
    private ImageButton startDateBtn;
    private TextView endDateView;
    private ImageButton endDateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        onClickBack();
        onEndDateButtonClicked();
        onStartDateButtonClicked();
        onSearchButtonClicked();

    }


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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Search.this, Home.class);
        startActivity(intent);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onStartDateButtonClicked() {

        startDateView = (TextView) findViewById(R.id.startDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        startDateView.setText(date);
        startDateBtn = (ImageButton) findViewById(R.id.startDateBtn);


        //what happens on expense button click
        startDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //pick the date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String mn ="";
                                String dy ="";

                                if(String.valueOf(dayOfMonth).length()==1){
                                    dy ="0";
                                }

                                if(String.valueOf(monthOfYear).length()==1){
                                    mn ="0";
                                }

                                startDateView.setText( year+ "-"
                                        +mn  + (monthOfYear + 1) + "-" + dy+dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }

    public void onEndDateButtonClicked() {

        endDateView = (TextView) findViewById(R.id.endDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        endDateView.setText(date);
        endDateBtn = (ImageButton) findViewById(R.id.endDateBtn);

        //what happens on expense button click
        endDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //pick the date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String mn ="";
                                String dy ="";

                                if(String.valueOf(dayOfMonth).length()==1){
                                    dy ="0";
                                }

                                if(String.valueOf(monthOfYear).length()==1){
                                    mn ="0";
                                }

                                endDateView.setText( year+ "-"
                                        +mn  + (monthOfYear + 1) + "-" + dy+dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }

    // what happens at the search button click
    public void onSearchButtonClicked() {

        Button searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String stDate = startDateView.getText().toString();
                String enDate = endDateView.getText().toString();
                ListView searchItemList = (ListView)findViewById(R.id.searchItemList);
                Schema sh = new Schema(getApplicationContext());

                ArrayList<String> ary = sh.getSearchList(stDate, enDate, "Sales");

                if (ary.isEmpty()) {

                    Toast toast = Toast.makeText(getApplicationContext(), "No Records to display", Toast.LENGTH_SHORT);
                    toast.show();

                } else {

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,ary);
                    searchItemList.setAdapter(adapter);

                }
            }
        });
    }
}
