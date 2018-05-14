package com.example.shanika.expensetracker;

import android.annotation.TargetApi;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class Search extends AppCompatActivity {
    private static ImageButton back;
    private static Date d1;
    private static Date d2;
    private TextView startDateView;
    private ImageButton startDateBtn;
    private TextView endDateView;
    private ImageButton endDateBtn;
    private String averageExpense, averageIncome;
    private String totalIncome, totalExpense;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    public void onStartDateButtonClicked() {

        startDateView = (TextView) findViewById(R.id.startDateView);
        final String date = new Functions().addDate();
        startDateView.setText(date);
        startDateBtn = (ImageButton) findViewById(R.id.startDateBtn);
        //what happens on expense button click
        startDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new Functions().getFormattedDate(c, startDateView, Search.this);

            }

        });
    }

    public void onEndDateButtonClicked() {

        endDateView = (TextView) findViewById(R.id.endDateView);
        String date = new Functions().addDate();
        endDateView.setText(date);
        endDateBtn = (ImageButton) findViewById(R.id.endDateBtn);
        //what happens on expense button click
        endDateBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new Functions().getFormattedDate(c, endDateView, Search.this);
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

                if (d1 == null) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(new Date());
                    d1 = cal.getTime();
                }

                if (d2 == null) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(new Date());
                    d2 = cal.getTime();
                }

                long diff = d2.getTime() - d1.getTime();
                int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if (d1.equals(d2) || days == 0) {
                    days = 1;
                }

                // Calculate the expence per day for the searched date period
                averageIncome = String.valueOf((Integer.parseInt(sh.calculateTotalIncome(stDate, enDate))) / days);
                // Calculate the income per day for the relavant period
                averageExpense = String.valueOf((Integer.parseInt(sh.calculateTotalExpense(stDate, enDate))) / days);
                // Calculate total income of the period
                totalIncome = sh.calculateTotalIncome(stDate, enDate);
                // Calculate total expense of the period
                totalExpense = sh.calculateTotalExpense(stDate, enDate);


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
                    setSummary();
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


    public void setSummary() {

        TextView tot_in_lbl = (TextView) findViewById(R.id.tot_in_lbl);
        TextView tot_in_val = (TextView) findViewById(R.id.tot_in_val);
        TextView tot_ex_lbl = (TextView) findViewById(R.id.tot_ex_lbl);
        TextView tot_ex_val = (TextView) findViewById(R.id.tot_ex_val);
        TextView avg_in_lbl = (TextView) findViewById(R.id.avg_in_lbl);
        TextView avg_in_val = (TextView) findViewById(R.id.avg_in_val);
        TextView avg_ex_lbl = (TextView) findViewById(R.id.avg_ex_lbl);
        TextView avg_ex_val = (TextView) findViewById(R.id.avg_ex_val);
        TextView balance_lbl = (TextView) findViewById(R.id.balance_lbl);
        TextView balance_val = (TextView) findViewById(R.id.balance_val);
        TextView list_name = (TextView) findViewById(R.id.list_name);

        tot_in_lbl.setText("  Total income for the period : ");
        tot_ex_lbl.setText("  Total expense for the period : ");
        avg_in_lbl.setText("  Average income of a day : ");
        avg_ex_lbl.setText("  Average expense of a day : ");
        balance_lbl.setText("  The balance for the period : ");
        list_name.setText("Transaction List");

        if (totalIncome == null) {
            totalIncome = "0";
        }
        if (totalExpense == null) {
            totalExpense = "0";
        }
        tot_in_val.setText("Rs. " + totalIncome + ".00");
        tot_ex_val.setText("Rs. " + totalExpense + ".00");
        avg_in_val.setText("Rs. " + averageIncome + ".00");
        avg_ex_val.setText("Rs. " + averageExpense + ".00");
        balance_val.setText("Rs. " + String.valueOf((Integer.parseInt(totalIncome) - Integer.parseInt(totalExpense))) + ".00");

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
}
