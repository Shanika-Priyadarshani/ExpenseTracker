package com.example.shanika.expensetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddIncomeRecord extends AppCompatActivity {

    //declare all the variables within the class

    public ImageButton back;
    private TextView dateview;
    private ImageButton dateBtn;
    private DatePickerDialog datePickerDialog;
    private TextView dateView;
    private TextView incomeAmount;
    private Spinner IncomecategorySet;
    private TextView IncomeDescription;
    private Button incomeAddBtn;
    private Button incomeCancelBtn;
    private Spinner spinner;
    private Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_record);
        onClickBack();
        onIncomeAddButtonClick();
        onIncomeCancelButtonClicked();
        context = getApplicationContext();

        ArrayList<String> ary = new Schema(context).getMenuList("Income");
        spinner = (Spinner) findViewById(R.id.IncomecategorySet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ary);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

      // set the date viewing and display todays date

        dateview = (TextView) findViewById(R.id.dateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        dateview.setText(date);
        dateBtn = (ImageButton) findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(AddIncomeRecord.this,
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

                                dateview.setText( year+ "-"
                                      +mn  + (monthOfYear + 1) + "-" + dy+dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent("com.example.shanika.expensetracker.Home");
        startActivity(intent);
        finish();
    }

    //what happens when adding a income
    public void onIncomeAddButtonClick() {


        // get the values of all the variables
        incomeAddBtn = (Button) findViewById(R.id.incomeAddBtn);
        IncomecategorySet = (Spinner) findViewById(R.id.IncomecategorySet);
        dateView = (TextView) findViewById(R.id.dateView);
        incomeAmount = (TextView) findViewById(R.id.incomeAmount);
        IncomeDescription = (TextView) findViewById(R.id.IncomeDescription);

        //set a listner to the add button

        incomeAddBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int duration = Toast.LENGTH_SHORT;

                        String category = IncomecategorySet.getSelectedItem().toString();
                        String date = dateView.getText().toString();
                        String description = IncomeDescription.getText().toString();
                        String amt = incomeAmount.getText().toString();
                        if (!amt.equals("")) {
                            Double amount = Double.parseDouble(amt);

                            //create database instance
                            DatabaseHelper dhelper = new DatabaseHelper(context);
                            boolean val = dhelper.insertIncomeRecord(date, category, amount, description);
                            //check for transaction success
                            if (val == true) {
                                incomeAmount.setText("");
                                IncomeDescription.setText("");
                                Toast toast = Toast.makeText(context, "Income Record added successfully", duration);
                                toast.show();

                            } else {
                                Toast toast = Toast.makeText(context, "Income Record addition faild. Please try again ", duration);
                                toast.show();
                            }
                        } else {
                            Toast toast = Toast.makeText(context, "Enter an amount and Retry ", duration);
                            toast.show();

                        }
                    }
                }

        );

    }

    //declare cancel button function
    public void onIncomeCancelButtonClicked() {
        incomeCancelBtn = (Button) findViewById(R.id.incomeCancelBtn);
        incomeCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddIncomeRecord.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }


}