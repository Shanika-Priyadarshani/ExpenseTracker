package com.example.shanika.expensetracker;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SetLimit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);


        TextView tx = (TextView) findViewById(R.id.glance);
        font(tx, "fonts/wet pet.ttf");
    }

    public void font(TextView tx, String s) {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), s);
        tx.setTypeface(custom_font);


    }
}