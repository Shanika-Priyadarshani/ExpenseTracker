package com.example.shanika.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddCategory extends AppCompatActivity {

    public static ImageButton back;
    private static Spinner type;
    private static TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        onClickBack();
        onAddButtonClick();
    }

    public void onClickBack() {

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent("com.example.shanika.expensetracker.Categories");
                        startActivity(intent);
                    }
                }

        );


    }

    public void onAddButtonClick(){

        ImageButton add= (ImageButton)findViewById(R.id.addRecord);
         type =(Spinner) findViewById(R.id.type);
         name=(TextView)findViewById(R.id.name);


        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String tp=type.getSelectedItem().toString();
                        String nm=name.getText().toString();
                        Context context= getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        DatabaseHelper dhelper= new DatabaseHelper(context);
                        boolean val=dhelper.insertCategory(nm,tp);
                         if (val==true){
                             Toast toast= Toast.makeText(context,"Category added successfully",duration);
                             toast.show();
                         }
                         else{
                             Toast toast= Toast.makeText(context,"Category addition faild. Please try again ",duration);
                             toast.show();
                         }

                    }
                }

        );

    }

    public void insertCategory(){


    }

}
