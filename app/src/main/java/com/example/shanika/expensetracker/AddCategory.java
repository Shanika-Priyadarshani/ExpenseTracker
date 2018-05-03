package com.example.shanika.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddCategory extends AppCompatActivity {

    public  ImageButton back;
    private  Spinner type;
    private  TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        onClickBack();
        onAddButtonClick();
        onCancelCategoryButtonClicked();
    }

    // declaring the function to back button- to go to previous class
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
        Intent intent = new Intent(AddCategory.this, Categories.class);
        startActivity(intent);
        finish();
    }

    // declare what happens when adding a category
    public void onAddButtonClick() {
        try {
            // get id of the button
            Button add = (Button) findViewById(R.id.addCategoryBtn);
            type = (Spinner) findViewById(R.id.type);
            name = (TextView) findViewById(R.id.name);

            // declare a on click listner to the function
            add.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // declare a context and a duration for the toasts to appea over the class
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_SHORT;
                            //get the selected type and entered name
                            String tp = type.getSelectedItem().toString();
                            String strn = name.getText().toString().trim();

                            if (!strn.isEmpty()) {
                                String nm = strn.substring(0, 1).toUpperCase() + strn.substring(1);
                                // create database instance
                                DatabaseHelper dhelper = new DatabaseHelper(context);
                                //call the data inserting function
                                boolean val = dhelper.insertCategory(nm, tp);
                                //if transaction succcessfull
                                if (val == true) {
                                    name.setText("");
                                    Toast toast = Toast.makeText(context, "Category added successfully", duration);
                                    toast.show();
                                    //if transaction fails
                                } else {
                                    Toast toast = Toast.makeText(context, "Category addition faild. Please try again ", duration);
                                    toast.show();
                                }

                            } else {
                                Toast toast = Toast.makeText(context, "Enter a name and Retry ", duration);
                                toast.show();

                            }

                        }
                    }

            );
        }
        catch (Exception e){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Something went wrong. Please retry!", duration);
            toast.show();
        }
    }

    // declare what happens on cancel button click- goto prevoius activity
    public void onCancelCategoryButtonClicked() {
        Button catgoryCancelBtn = (Button) findViewById(R.id.catgoryCancelBtn);
        catgoryCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategory.this, Categories.class);
                startActivity(intent);
                finish();
            }
        });

    }


}


