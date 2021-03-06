package com.example.shanika.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;


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
        ArrayList<ArrayList<String>> ary = sh.getHistoryList(type);
        final int size = ary.size();
        if (ary.get(1).isEmpty() || size == 0) {
            history_list.setAdapter(null);
            Toast toast = Toast.makeText(getApplicationContext(), "No Records to display", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            final ArrayList<String> category= ary.get(0);
            final ArrayList<String> date= ary.get(1);
            final ArrayList<String> amount= ary.get(2);
            final ArrayList<String> description= ary.get(3);

            class CustomAdaptor extends BaseAdapter{

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

                    view=getLayoutInflater().inflate(R.layout.custom_layout,null);
                    ImageView lay_image = (ImageView) view.findViewById(R.id.lay_cat_image);
                    TextView lay_cat_name =(TextView)view. findViewById(R.id.lay_cat_name);
                    TextView lay_date =(TextView)view. findViewById(R.id.lay_date);
                    TextView lay_amount =(TextView)view. findViewById(R.id.lay_amount);
                    TextView lay_description =(TextView)view. findViewById(R.id.lay_description);

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
                    lay_amount.setText("Rs. "+amount.get(position));
                    lay_description.setText(description.get(position));

                    return view;
                }
            }


            CustomAdaptor customAdaptor =new CustomAdaptor();
            history_list.setAdapter(customAdaptor);
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
