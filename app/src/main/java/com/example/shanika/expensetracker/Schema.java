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
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }


    public ArrayList<String> getMenuList(String type) {

        menuSet = new ArrayList<>();
        Cursor cursor;
        if (type == "") {
            cursor = db.rawQuery("Select CAT_NAME from Category order by CAT_NAME", null);
        } else {
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
        searchList = new ArrayList<>();
        Cursor additional;
        Cursor cursor;

        if (type.equals("All Categories")){

            cursor =db.rawQuery("Select * from Income where  DATE between '" + start + "' AND '" + end + "'UNION Select * from Expense where DATE between '" + start + "' AND '" + end + "'order by DATE ",null);

        }

        else if (type.equals("All Incomes")){

            cursor =db.rawQuery("Select * from Income where  DATE between '" + start + "' AND '" + end + "'order by DATE",null);

        }

        else if (type.equals("All Expenses")){

            cursor =db.rawQuery("Select * from Expense where DATE between '" + start + "' AND '" + end + "'order by DATE",null);

        }

        else {
            additional = db.rawQuery("Select TYPE from Category where CAT_NAME='" + type + "'", null);
            additional.moveToFirst();
            String inEx = additional.getString(additional.getColumnIndex("TYPE"));
            cursor = db.rawQuery("Select * from '" + inEx + "'where Category='" + type + "' and DATE between '" + start + "' AND '" + end + "'order by DATE", null);
        }

        while (cursor.moveToNext()) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Category : " + cursor.getString(cursor.getColumnIndex("CATEGORY")) + "\n");
            buffer.append("Date : " + cursor.getString(cursor.getColumnIndex("DATE")) + "\n");
            buffer.append("Amount : " + cursor.getString(cursor.getColumnIndex("AMOUNT")) + "\n");
            buffer.append("Description : " + cursor.getString(cursor.getColumnIndex("DESCRIPTION")) + "\n\n");

            searchList.add(buffer.toString());
        }

        cursor.close();
        db.close();

        return searchList;
    }

    public ArrayList<ArrayList<String>> getCategoryList() {
        ArrayList<String> incomeSet = new ArrayList<>();
        ArrayList<String> expenseSet = new ArrayList<>();
        ArrayList<ArrayList<String>> sets = new ArrayList<>();
        Cursor cursorIn;
        Cursor cursorEx;

        cursorIn = db.rawQuery("Select CAT_NAME from Category where TYPE='Income' order by CAT_NAME", null);
        cursorEx = db.rawQuery("Select CAT_NAME from Category where TYPE='Expense' order by CAT_NAME", null);

        while (cursorIn.moveToNext()) {
            String data;
            data = cursorIn.getString(cursorIn.getColumnIndex("CAT_NAME"));
            incomeSet.add(data);
        }

        while (cursorEx.moveToNext()) {
            String data;
            data = cursorEx.getString(cursorEx.getColumnIndex("CAT_NAME"));
            expenseSet.add(data);
        }

        sets.add(incomeSet);
        sets.add(expenseSet);
        cursorIn.close();
        System.out.println(incomeSet);
        System.out.println(expenseSet);
        cursorEx.close();
        db.close();
        return sets;
    }



    public ArrayList<String> getHistoryList(String type) {
        searchList = new ArrayList<>();
        Cursor additional;
        Cursor cursor;

        if (type.equals("All Categories")){

            cursor =db.rawQuery("Select * from Income UNION Select * from Expense order by DATE DESC Limit 100",null);

        }

        else if (type.equals("All Incomes")){

            cursor =db.rawQuery("Select * from Income order by DATE DESC LIMIT 50",null);

        }

        else if (type.equals("All Expenses")){

            cursor =db.rawQuery("Select * from Expense order by DATE DESC  LIMIT 50",null);

        }

        else {
            additional = db.rawQuery("Select TYPE from Category where CAT_NAME='" + type + "'", null);
            additional.moveToFirst();
            String inEx = additional.getString(additional.getColumnIndex("TYPE"));
            cursor = db.rawQuery("Select * from '" + inEx + "'where Category='" + type + "'order by DATE DESC LIMIT 30", null);
        }

        while (cursor.moveToNext()) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Category : " + cursor.getString(cursor.getColumnIndex("CATEGORY")) + "\n");
            buffer.append("Date : " + cursor.getString(cursor.getColumnIndex("DATE")) + "\n");
            buffer.append("Amount : " + cursor.getString(cursor.getColumnIndex("AMOUNT")) + "\n");
            buffer.append("Description : " + cursor.getString(cursor.getColumnIndex("DESCRIPTION")) + "\n\n");

            searchList.add(buffer.toString());
        }

        cursor.close();
        db.close();

        return searchList;

    }
}
