package com.example.shanika.expensetracker;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


/**
 * Created by Shanika on 5/7/2018.
 */

public class Warning extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button ok;


    public Warning(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.warning);
        ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                dismiss();
                break;
        }
        dismiss();
    }

    public double checkExeeded() {
        ArrayList<String> dates = new Functions().getDateRange();
        String start = dates.get(0);
        String end = dates.get(1);
        Schema sh = new Schema(getContext());
        double totalExpenseUpToNow = Double.parseDouble(sh.calculateTotalExpense(start, end));
        return totalExpenseUpToNow;
    }
}
