package com.example.shanika.expensetracker;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class History extends AppCompatActivity {
    private static ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        onClickBack();
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
        Intent intent = new Intent(History.this, Home.class);
        startActivity(intent);
        finish();
    }
}
