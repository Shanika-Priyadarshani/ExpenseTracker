package com.example.shanika.expensetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class SetLimit extends AppCompatActivity {

    private static ImageButton back;
    private static String limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        limit = sharedPreferences.getString("limit", "0");
        EditText edit = (EditText)findViewById(R.id.value);
        edit.setText(limit);

        TextView tx = (TextView) findViewById(R.id.glance);
        font(tx, "fonts/wet pet.ttf");
        onLimitCancelButtonClicked();
        onDoneLimitButtonClicked();
        onClickBack();

    }

    public void font(TextView tx, String s) {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), s);
        tx.setTypeface(custom_font);

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
    public void onBackPressed(){
        Intent intent = new Intent(SetLimit.this, Home.class);
        startActivity(intent);
        finish();
    }


    public void onLimitCancelButtonClicked() {
        Button limitCancelBtn = (Button) findViewById(R.id.limitCancelBtn);
        limitCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetLimit.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onDoneLimitButtonClicked(){

        Button doneLimitBtn =(Button)findViewById(R.id.doneLimitBtn);
        doneLimitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value = (EditText) findViewById(R.id.value);

                String amount = value.getText().toString();
                if (amount.equals("") || amount.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid amount & Retry", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (amount.equals("") || amount.isEmpty()) {
                        amount = "0";
                    }
                    editor.putString("limit", amount);
                    editor.apply();

                    String ss = sharedPreferences.getString("limit", "0");
                    Toast toast = Toast.makeText(getApplicationContext(), "Rs." + ss + " Successfully set as Monthly Limit", Toast.LENGTH_SHORT);
                toast.show();
            }
            }
        });
    }


}