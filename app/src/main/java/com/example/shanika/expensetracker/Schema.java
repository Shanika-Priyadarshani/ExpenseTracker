package com.example.shanika.expensetracker;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Schema {


    public ArrayList<String> menuSet;
    SQLiteDatabase db;

    public Schema(Context context) {
        menuSet = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }


    public ArrayList<String> getMenuList(String type) {

        Cursor cursor;
        if (type==""){
            cursor = db.rawQuery("Select CAT_NAME from Category order by CAT_NAME", null);
        }
        else {
             cursor = db.rawQuery("Select CAT_NAME from Category where TYPE='" + type + "' order by CAT_NAME", null);
        }
            while (cursor.moveToNext()) {
                String data;
                data = cursor.getString(cursor.getColumnIndex("CAT_NAME"));
                menuSet.add(data);

            }
            cursor.close();
            db.close();

            return menuSet;
        }




}
