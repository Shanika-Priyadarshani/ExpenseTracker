package com.example.shanika.expensetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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


public class AddExpenseRecord extends AppCompatActivity {


    public ImageButton back;
    private ImageButton expenseDateBtn;
    private DatePickerDialog datePickerDialog;
    private TextView expenseDateView;
    private TextView expenseAmount;
    private Spinner expenseCategorySet;
    private TextView expenseDescription;
    private Button expenseAddBtn;
    private Button expenseCancelBtn;
    private Context context;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_record);
        savingsCalculation();
        onClickBack();
        onExpenseAddButtonClick();
        onExpenseCancelButtonClicked();

        context = getApplicationContext();

        ArrayList<String> ary = new Schema(context).getMenuList("Expense");
        spinner = (Spinner) findViewById(R.id.expenseCategorySet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ary);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //take all the views from the interface
        expenseDateView = (TextView) findViewById(R.id.expenseDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        expenseDateView.setText(date);
        expenseDateBtn = (ImageButton) findViewById(R.id.expenseDateBtn);

        //what happens on expense button click
        expenseDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //pick the date
                datePickerDialog = new DatePickerDialog(AddExpenseRecord.this,
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

                                expenseDateView.setText( year+ "-"
                                        +mn  + (monthOfYear + 1) + "-" + dy+dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    //what the clicking back button do
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
   //what happens on adding a record
    public void onExpenseAddButtonClick() {

        expenseAddBtn = (Button) findViewById(R.id.expenseAddBtn);
        expenseCategorySet = (Spinner) findViewById(R.id.expenseCategorySet);
        expenseAmount = (TextView) findViewById(R.id.expenseAmount);
        expenseDescription = (TextView) findViewById(R.id.expenseDescription);

        //set the button listner
        expenseAddBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        int duration = Toast.LENGTH_SHORT;

                        //get the entered values
                        String category = expenseCategorySet.getSelectedItem().toString();
                        String date = expenseDateView.getText().toString();
                        String description = expenseDescription.getText().toString();
                        String amt = expenseAmount.getText().toString();

                        if (!amt.equals("")) {

                            Double amount = Double.parseDouble(amt);

                            //create database instance
                            DatabaseHelper dhelper = new DatabaseHelper(context);
                            boolean val = dhelper.insertExpenseRecord(date, category, amount, description);

                            //check the transaction success
                            if (val) {
                                Toast toast = Toast.makeText(context, "New Expense Record added successfully", duration);
                                toast.show();
                                expenseAmount.setText("");
                                expenseDescription.setText("");

                                Warning warning = new Warning(AddExpenseRecord.this);
                                double valueUptoNow = warning.checkExeeded();
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                double limit = Double.parseDouble(sharedPreferences.getString("limit", "0"));
                                if (limit != 0 && Double.valueOf(limit).toString() != "") {
                                    if (valueUptoNow > limit) {
                                        warning.show();
                                    }
                                }
                            } else {
                                Toast toast = Toast.makeText(context, "Expense Record addition faild. Please try again ", duration);
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

    public void onExpenseCancelButtonClicked() {
        expenseCancelBtn = (Button) findViewById(R.id.expenseCancelBtn);
        expenseCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExpenseRecord.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void savingsCalculation() {
        Suggession suggession = new Suggession(AddExpenseRecord.this);
        ArrayList<Double> lastMonthIncomeExpence = suggession.checkSavings();
        double lastMonthIncome = lastMonthIncomeExpence.get(1);
        double lastMonthExpense = lastMonthIncomeExpence.get(0);
        double lastMonthSaving = lastMonthIncome - lastMonthExpense;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double limit = Double.parseDouble(sharedPreferences.getString("limit", "0"));
        if (limit != 0 && Double.valueOf(limit).toString() != "") {
            if (lastMonthExpense < limit && lastMonthSaving > (lastMonthIncome / 4)) {
                suggession.show();
            }
        }
    }


}
