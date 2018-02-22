package com.example.shanika.expensetracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    DatabaseHelper myDB;

    private ImageButton btn_nav;
    private ImageButton back;
    private NavigationView navigation;
    private ListView set_list;
    private ImageButton settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDB=new DatabaseHelper(this);

        onClickButtonNav();
        onClickBack();


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

    public void onClickBack() {
        btn_nav = (ImageButton) findViewById(R.id.nav);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigation = (NavigationView) findViewById(R.id.navigation);
                        navigation.setVisibility(View.INVISIBLE);
                        btn_nav.setVisibility(View.VISIBLE);
                        back.setVisibility(View.INVISIBLE);
                    }
                }

        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
     getMenuInflater().inflate(R.menu.sidebar_navigation,menu);
        getMenuInflater().inflate(R.menu.navigation_item,menu);

        return  true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if (id==R.id.income){
            return true;
        }

        if (id==R.id.expense){
            return true;
        }

        if (id==R.id.search){
            return true;
        }
        if (id==R.id.history){
            return true;
        }

        if (id==R.id.limit){
            return true;
        }

        if (id==R.id.frequent){
            return true;
        }

        if (id==R.id.categories){
            return true;
        }

                return super.onOptionsItemSelected(item);

    }


    public void searchButtonClicked(MenuItem item){
        Intent intent= new Intent(this,Search.class);
        this.startActivity(intent);

    }

    public void historyButtonClicked(MenuItem item){
        Intent intent= new Intent(this,History.class);
        this.startActivity(intent);

    }
    public void categoriesButtonClicked(MenuItem item){
        Intent intent= new Intent(this,Categories.class);
        this.startActivity(intent);

    } public void limitButtonClicked(MenuItem item){
        Intent intent= new Intent(this,SetLimit.class);
        this.startActivity(intent);

    } public void frequentButtonClicked(MenuItem item){
        Intent intent= new Intent(this,FrequentRecordDisplay.class);
        this.startActivity(intent);
    }

    public void AddIncomeButtonClicked(MenuItem item){
        Intent intent= new Intent(this,NewRecord.class);
        this.startActivity(intent);
    }

    public void AddExpenseButtonClicked(MenuItem item){
        Intent intent= new Intent(this,NewRecord.class);
        this.startActivity(intent);
        Spinner ExpenseCategories= (Spinner)findViewById(R.id.categorySet);
        NewRecord nwr= new NewRecord();
        nwr.changeType();



    }

}
