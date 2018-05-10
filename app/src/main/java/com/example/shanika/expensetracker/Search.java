package com.example.shanika.expensetracker;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

public class Search extends AppCompatActivity {
    private static ImageButton back;
    private TextView startDateView;
    private ImageButton startDateBtn;
    private TextView endDateView;
    private ImageButton endDateBtn;

    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        type = "All Categories";
        onClickBack();
        onEndDateButtonClicked();
        onStartDateButtonClicked();
        onSearchButtonClicked();

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
        Intent intent = new Intent(Search.this, Home.class);
        startActivity(intent);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onStartDateButtonClicked() {

        startDateView = (TextView) findViewById(R.id.startDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        startDateView.setText(date);
        startDateBtn = (ImageButton) findViewById(R.id.startDateBtn);


        //what happens on expense button click
        startDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //pick the date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String mn = "";
                                String dy = "";

                                if (String.valueOf(dayOfMonth).length() == 1) {
                                    dy = "0";
                                }

                                if (String.valueOf(monthOfYear).length() == 1) {
                                    mn = "0";
                                }

                                startDateView.setText(year + "-"
                                        + mn + (monthOfYear + 1) + "-" + dy + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }

    public void onEndDateButtonClicked() {

        endDateView = (TextView) findViewById(R.id.endDateView);
        Functions fn = new Functions();
        String date = fn.addDate();
        endDateView.setText(date);
        endDateBtn = (ImageButton) findViewById(R.id.endDateBtn);

        //what happens on expense button click
        endDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                //pick the date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String mn = "";
                                String dy = "";

                                if (String.valueOf(dayOfMonth).length() == 1) {
                                    dy = "0";
                                }

                                if (String.valueOf(monthOfYear).length() == 1) {
                                    mn = "0";
                                }

                                endDateView.setText(year + "-"
                                        + mn + (monthOfYear + 1) + "-" + dy + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });
    }

    // what happens at the search button click
    public void onSearchButtonClicked() {

        Button searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String stDate = startDateView.getText().toString();
                String enDate = endDateView.getText().toString();
                ListView searchItemList = (ListView) findViewById(R.id.searchItemList);
                Schema sh = new Schema(getApplicationContext());

                ArrayList<ArrayList<String>> ary = sh.getSearchList(stDate, enDate, type);
                final int size = ary.size();
                if (ary.get(1).isEmpty() || size == 0) {

                    searchItemList.setAdapter(null);
                    Toast toast = Toast.makeText(getApplicationContext(), "No Records to display", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    final ArrayList<String> category = ary.get(0);
                    final ArrayList<String> date = ary.get(1);
                    final ArrayList<String> amount = ary.get(2);
                    final ArrayList<String> description = ary.get(3);

                    class CustomAdaptor extends BaseAdapter {


                        @Override
                        public int getCount() {
                            return category.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return null;
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View view, ViewGroup parent) {

                            view = getLayoutInflater().inflate(R.layout.custom_layout, null);
                            ImageView lay_image = (ImageView) view.findViewById(R.id.lay_cat_image);
                            TextView lay_cat_name = (TextView) view.findViewById(R.id.lay_cat_name);
                            TextView lay_date = (TextView) view.findViewById(R.id.lay_date);
                            TextView lay_amount = (TextView) view.findViewById(R.id.lay_amount);
                            TextView lay_description = (TextView) view.findViewById(R.id.lay_description);

                            Context context = lay_image.getContext();
                            Field[] fields = R.drawable.class.getFields();
                            String imageName = category.get(position).toLowerCase();
                            int id = 0;
                            Boolean found = false;
                            for (Field field : fields) {
                                if (field.getName().equals(imageName)) {
                                    id = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                imageName = Character.toString(category.get(position).toLowerCase().charAt(0));
                                id = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
                            }
                            lay_image.setImageResource(id);
                            lay_cat_name.setText(category.get(position));
                            lay_date.setText(date.get(position));
                            lay_amount.setText("Rs. " + amount.get(position));
                            lay_description.setText(description.get(position));

                            return view;
                        }
                    }


                    CustomAdaptor customAdaptor = new CustomAdaptor();
                    searchItemList.setAdapter(customAdaptor);

                }
            }
        });
    }

    public void onSearchMenuClicked(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_types, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ImageButton searchCatMenu = (ImageButton) findViewById(R.id.searchCatMenu);

                Schema sh = new Schema(getApplicationContext());
                ArrayList<ArrayList<String>> sets = sh.getCategoryList();
                ArrayList<String> incomeSet = sets.get(0);
                ArrayList<String> expenseSet = sets.get(1);

                if (item.getTitle().equals("Income")) {

                    PopupMenu popupIncome = new PopupMenu(Search.this, searchCatMenu);
                    popupIncome.getMenu().add("All Incomes");
                    for (String s : incomeSet) {
                        popupIncome.getMenu().add(s);
                    }

                    popupIncome.show();

                    popupIncome.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            type = item.getTitle().toString();
                            return false;
                        }
                    });

                }

                if (item.getTitle().equals("Expense")) {
                    PopupMenu popupExpense = new PopupMenu(Search.this, searchCatMenu);
                    popupExpense.getMenu().add("All Expenses");

                    for (String s : expenseSet) {
                        popupExpense.getMenu().add(s);
                    }
                    popupExpense.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            type = item.getTitle().toString();
                            return false;
                        }
                    });

                    popupExpense.show();

                }

                if (item.getTitle().equals("All Categories")) {
                    type = "All Categories";
                }
                return false;
            }
        });


    }
}
