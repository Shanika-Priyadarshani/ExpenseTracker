package com.example.shanika.expensetracker;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Categories extends AppCompatActivity {

    private static ImageButton back;
    private static ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
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
        Intent intent = new Intent(Categories.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void onClickAdd() {

        add = (ImageButton) findViewById(R.id.add);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.shanika.expensetracker.AddCategory");
                        startActivity(intent);
                        finish();

                    }
                }

        );


    }
}
