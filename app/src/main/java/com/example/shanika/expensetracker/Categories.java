package com.example.shanika.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
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
        final ArrayList<String> incomeSet = sets.get(0);
        final ArrayList<String> expenseSet = sets.get(1);
        ListView incomeItemList = (ListView)findViewById(R.id.incomeCatList);
        ListView expenseItemList = (ListView)findViewById(R.id.expenseCatList);

        class CustomAdaptor extends BaseAdapter {
            ArrayList<String> list;

            public CustomAdaptor(ArrayList<String> list) {
                this.list = list;
            }

            @Override
            public int getCount() {
                return list.size();
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

                view = getLayoutInflater().inflate(R.layout.category_layout, null);
                ImageView lay_cat_image = (ImageView) view.findViewById(R.id.lay_cat_image);
                ;
                TextView lay_cat_name = (TextView) view.findViewById(R.id.lay_cat_name);

                Context context = lay_cat_image.getContext();
                Field[] fields = R.drawable.class.getFields();
                String imageName = list.get(position).toLowerCase();
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
                    imageName = Character.toString(list.get(position).toLowerCase().charAt(0));
                    id = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
                }
                lay_cat_image.setImageResource(id);
                lay_cat_name.setText(list.get(position));
                return view;
            }
        }


        CustomAdaptor customAdaptor = new CustomAdaptor(expenseSet);
        expenseItemList.setAdapter(customAdaptor);
        CustomAdaptor customAdaptor2 = new CustomAdaptor(incomeSet);
        incomeItemList.setAdapter(customAdaptor2);

    }
}
