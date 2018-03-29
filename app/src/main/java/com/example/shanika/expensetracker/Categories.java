package com.example.shanika.expensetracker;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    private static ImageButton back;
    private static ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        display();
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

    public void display(){

        Schema sh = new Schema(getApplicationContext());
        ArrayList<ArrayList<String>> sets =sh.getCategoryList();
        ArrayList<String> incomeSet =sets.get(0);
        ArrayList<String> expenseSet =sets.get(1);
        ListView incomeItemList = (ListView)findViewById(R.id.incomeCatList);
        ListView expenseItemList = (ListView)findViewById(R.id.expenseCatList);



        ArrayAdapter<String> incomeAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,incomeSet);
        System.out.println(incomeSet);
        System.out.println(expenseSet);
        incomeItemList.setAdapter(incomeAdapter);
        ArrayAdapter<String> expenseAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,expenseSet);
        expenseItemList.setAdapter(expenseAdapter);

    }
}
