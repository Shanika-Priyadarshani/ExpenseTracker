package com.example.shanika.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewRecord extends AppCompatActivity {

    public static ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        onClickBack();
    }

    public void onClickBack() {

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent("com.example.shanika.expensetracker.Home");
                        startActivity(intent);
                    }
                }

        );


    }

    public void changeType(){
        String string = getString(R.string.title);
        TextView title= (TextView)findViewById(R.id.addArea);
        title.setText(string);
    }
}
