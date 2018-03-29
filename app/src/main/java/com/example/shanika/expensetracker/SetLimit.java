package com.example.shanika.expensetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SetLimit extends AppCompatActivity {

    private static ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String ss=sharedPreferences.getString("value","failed");
        EditText edit = (EditText)findViewById(R.id.value);
        edit.setText(ss);

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
              EditText value =(EditText)findViewById(R.id.value);

                String amount =value.getText().toString();
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("value",amount);
                editor.commit();

                String ss=sharedPreferences.getString("value","failed");
                Toast toast = Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}