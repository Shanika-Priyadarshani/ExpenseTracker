package com.example.shanika.expensetracker;

import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
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


}
