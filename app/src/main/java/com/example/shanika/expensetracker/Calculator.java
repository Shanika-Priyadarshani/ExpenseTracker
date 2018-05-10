package com.example.shanika.expensetracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Calculator extends AppCompatActivity {

    private static ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getApplicationContext()));
        setContentView(R.layout.activity_calculator);
        onClickBack();

        final EditText edt1= (EditText) findViewById(R.id.edt1);;

        Button button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
                buttonMul, button10, buttonC, buttonEqual;


        final float[] mValueOne = new float[1];
        final float[] mValueTwo = new float[1];
        final boolean[] mAddition = new boolean[1];
        final boolean[] mSubtract = new boolean[1];
        final boolean[] mMultiplication = new boolean[1];
        final boolean[] mDivision = new boolean[1];

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);



            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "1");
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "2");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "3");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "4");
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "5");
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "6");
                }
            });

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "7");
                }
            });

            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "8");
                }
            });

            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "9");
                }
            });

            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + "0");
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edt1 == null) {
                        edt1.setText("");
                    } else {
                        mValueOne[0] = Float.parseFloat(edt1.getText() + "");
                        mAddition[0] = true;
                        edt1.setText(null);
                    }
                }
            });

            buttonSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValueOne[0] = Float.parseFloat(edt1.getText() + "");
                    mSubtract[0] = true;
                    edt1.setText(null);
                }
            });

            buttonMul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValueOne[0] = Float.parseFloat(edt1.getText() + "");
                    mMultiplication[0] = true;
                    edt1.setText(null);
                }
            });

            buttonDivision.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValueOne[0] = Float.parseFloat(edt1.getText() + "");
                    mDivision[0] = true;
                    edt1.setText(null);
                }
            });

            buttonEqual.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    mValueTwo[0] = Float.parseFloat(edt1.getText() + "");

                    if (mAddition[0] == true) {

                        edt1.setText(mValueOne[0] + mValueTwo[0] + "");
                        mAddition[0] = false;
                    }


                    if (mSubtract[0] == true) {
                        edt1.setText(mValueOne[0] - mValueTwo[0] + "");
                        mSubtract[0] = false;
                    }

                    if (mMultiplication[0] == true) {
                        edt1.setText(mValueOne[0] * mValueTwo[0] + "");
                        mMultiplication[0] = false;
                    }

                    if (mDivision[0] == true) {
                        edt1.setText(mValueOne[0] / mValueTwo[0] + "");
                        mDivision[0] = false;
                    }


                }
            });

            buttonC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText("");
                }
            });

            button10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText(edt1.getText() + ".");
                }
            });


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Calculator.this, Home.class);
        startActivity(intent);
        finish();
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

    public void displayError(EditText edit1) {
        Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid Input", Toast.LENGTH_SHORT);
        edit1.setText(null);
        toast.show();
    }


}

