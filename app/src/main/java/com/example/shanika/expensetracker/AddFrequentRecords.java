package com.example.shanika.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddFrequentRecords extends AppCompatActivity {

    private static ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frequent_records);
        onClickBack();

    }

    public void onClickBack() {

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent("com.example.shanika.expensetracker.FrequentRecordDisplay");
                        startActivity(intent);
                    }
                }

        );


    }
}
