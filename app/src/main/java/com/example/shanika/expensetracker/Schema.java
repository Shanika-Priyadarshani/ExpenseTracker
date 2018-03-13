package com.example.shanika.expensetracker;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Schema {


    public ArrayList<String> menuSet;
    SQLiteDatabase db;
    ArrayList<String> searchList;

    public Schema(Context context) {
        menuSet = new ArrayList<>();
        searchList =new ArrayList<>();
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

    public ArrayList<String> getSearchList(String start, String end, String type) {

        Cursor additional;
        additional = db.rawQuery("Select TYPE from Category where CAT_NAME='"+type+"'",null);
        additional.moveToFirst();
        String inEx= additional.getString(additional.getColumnIndex("TYPE"));
        Cursor cursor;

        cursor = db.rawQuery("Select * from '"+ inEx+"'where Category='"+type+"' and DATE between '"+start+"' AND '"+end+"'order by DATE", null);

        while (cursor.moveToNext()) {
            StringBuffer  buffer = new StringBuffer();

            buffer.append("Category : "+cursor.getString(cursor.getColumnIndex("CATEGORY"))+"\n");
            buffer.append("Date : "+cursor.getString(cursor.getColumnIndex("DATE"))+"\n");
            buffer.append("Amount : "+cursor.getString(cursor.getColumnIndex("AMOUNT"))+"\n");
            buffer.append("Description : "+cursor.getString(cursor.getColumnIndex("DESCRIPTION"))+"\n\n");

            searchList.add(buffer.toString());
        }

        cursor.close();
        db.close();

        return searchList;
    }

}
