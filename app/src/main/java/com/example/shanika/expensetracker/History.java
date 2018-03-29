package com.example.shanika.expensetracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.shanika.expensetracker.R.drawable.plus;


public class History extends AppCompatActivity {
    private static ImageButton back;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        type = "All Categories";
        displayHistory(type);
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
    public void onBackPressed() {
        Intent intent = new Intent(History.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void displayHistory(String type) {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(type);

        ListView history_list = (ListView) findViewById(R.id.history_list);
        Schema sh = new Schema(getApplicationContext());

        ArrayList<String> ary = sh.getHistoryList(type);
        final int size = ary.size();

        if (ary.isEmpty()) {

            history_list.setAdapter(null);
            Toast toast = Toast.makeText(getApplicationContext(), "No Records to display", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ary) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(Color.BLUE);
                    textView.setBackgroundColor(Color.GRAY);

                    return view;
                }

            };
            history_list.setAdapter(adapter);


        }
    }

    public void onHistoryMenuClicked(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_types, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ImageButton historyType = (ImageButton) findViewById(R.id.historyType);

                Schema sh = new Schema(getApplicationContext());
                ArrayList<ArrayList<String>> sets = sh.getCategoryList();
                ArrayList<String> incomeSet = sets.get(0);
                ArrayList<String> expenseSet = sets.get(1);

                if (item.getTitle().equals("Income")) {

                    PopupMenu popupIncome = new PopupMenu(History.this, historyType);
                    popupIncome.getMenu().add("All Incomes");
                    for (String s : incomeSet) {
                        popupIncome.getMenu().add(s);
                    }

                    popupIncome.show();

                    popupIncome.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            type = item.getTitle().toString();
                            displayHistory(type);
                            return false;

                        }
                    });

                }

                if (item.getTitle().equals("Expense")) {
                    PopupMenu popupExpense = new PopupMenu(History.this, historyType);
                    popupExpense.getMenu().add("All Expenses");

                    for (String s : expenseSet) {
                        popupExpense.getMenu().add(s);
                    }
                    popupExpense.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            type = item.getTitle().toString();
                            displayHistory(type);
                            return false;
                        }
                    });

                    popupExpense.show();

                }

                if (item.getTitle().equals("All Categories")) {
                    type = "All Categories";
                    displayHistory(type);
                }

                return false;
            }

        });


    }
}
