package com.example.shanika.expensetracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Home extends AppCompatActivity {

    DatabaseHelper myDB;

    private ImageButton btn_nav;
    private ImageButton back;
    private NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDB = new DatabaseHelper(this);

        onClickButtonNav();
        onClickBack();
        onDates();


        TextView tx = (TextView) findViewById(R.id.glance);
        font(tx, "fonts/wet pet.ttf");

        TextView tx2 = (TextView) findViewById(R.id.overall);
        TextView tx3 = (TextView) findViewById(R.id.graphTitle);
        font(tx2, "fonts/MING.ttf");
        font(tx3, "fonts/MING.ttf");
        TextView tx4 = (TextView) findViewById(R.id.in);
        TextView tx5 = (TextView) findViewById(R.id.re);
        TextView tx6 = (TextView) findViewById(R.id.ex);
        TextView tx7 = (TextView) findViewById(R.id.bal);
        font(tx4, "fonts/Arturo-Bold Trial.ttf");
        font(tx5, "fonts/Arturo-Bold Trial.ttf");
        font(tx6, "fonts/Arturo-Bold Trial.ttf");
        font(tx7, "fonts/Arturo-Bold Trial.ttf");



    }

    public void font(TextView tx, String s) {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), s);
        tx.setTypeface(custom_font);
    }

    public void onClickButtonNav() {
        btn_nav = (ImageButton) findViewById(R.id.nav);
        back = (ImageButton) findViewById(R.id.back);
        btn_nav.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigation = (NavigationView) findViewById(R.id.navigation);
                        navigation.setVisibility(View.VISIBLE);
                        btn_nav.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                    }
                }

        );

    }

    private void onNavigationLeave() {
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setVisibility(View.INVISIBLE);
        btn_nav.setVisibility(View.VISIBLE);
        back.setVisibility(View.INVISIBLE);
    }

    public void onClickBack() {
        btn_nav = (ImageButton) findViewById(R.id.nav);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNavigationLeave();
                    }
                }

        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidebar_navigation, menu);
        getMenuInflater().inflate(R.menu.navigation_item, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.income) {
            return true;
        }
       if (id == R.id.calculator) {
            return true;
        }

        if (id == R.id.expense) {
            return true;
        }

        if (id == R.id.search) {
            return true;
        }
        if (id == R.id.history) {
            return true;
        }

        if (id == R.id.limit) {
            return true;
        }

        if (id == R.id.frequent) {
            return true;
        }

        if (id == R.id.categories) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void searchButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Search.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void historyButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, History.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();


    }

    public void categoriesButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Categories.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void limitButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, SetLimit.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void frequentButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, FrequentRecordDisplay.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void AddIncomeButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, AddIncomeRecord.class);
        this.startActivity(intent);
        finish();
    }

    public void AddExpenseButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, AddExpenseRecord.class);
        this.startActivity(intent);
        finish();

    }

   public void CalculatorButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Calculator.class);
        this.startActivity(intent);
       finish();

    }

    int backCount=0;
    @Override
    public void onBackPressed(){

        if (backCount==0){
            Toast toast = Toast.makeText(this.getApplicationContext(), "Press Back again to exit.", Toast.LENGTH_SHORT);
            toast.show();
            backCount+=1;
        }
        else{
            finish();
        }

    }

    public void onDates(){
                ArrayList<String> dates =new Functions().getDateRange();
                String start = dates.get(0);
                String end = dates.get(1);
                Schema sh = new Schema(getApplicationContext());

                String totalIncome=sh.calculateTotalIncome(start,end);
                String totalExpense=sh.calculateTotalExpense(start,end);

                TextView income =(TextView)findViewById(R.id.income);
                income.setText(" : "+totalIncome);

                TextView expense =(TextView)findViewById(R.id.expense);
                expense.setText(" : "+totalExpense);

                Toast toast = Toast.makeText(getApplicationContext(), start+"------------"+end, Toast.LENGTH_SHORT);
                toast.show();

            }

}


