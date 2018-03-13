package com.example.shanika.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FrequentRecordDisplay extends AppCompatActivity {

    private static ImageButton back;
    private static ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_record_display);
        onClickBack();
        onClickAdd();
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
        Intent intent = new Intent(FrequentRecordDisplay.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void onClickAdd() {

        add = (ImageButton) findViewById(R.id.add);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.shanika.expensetracker.AddFrequentRecords");
                        startActivity(intent);
                        finish();

                    }
                }

        );


    }
}
