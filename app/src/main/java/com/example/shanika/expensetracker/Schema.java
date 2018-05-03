package com.example.shanika.expensetracker;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

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



    public ArrayList<ArrayList<String>> getHistoryList(String type) {
        ArrayList<String> category= new ArrayList<>();
        ArrayList<String> date= new ArrayList<>();
        ArrayList<String> amount= new ArrayList<>();
        ArrayList<String> description= new ArrayList<>();
        ArrayList<ArrayList<String>> historyList = new  ArrayList<>();

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
            category.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            date.add(cursor.getString(cursor.getColumnIndex("DATE")));
            amount.add( cursor.getString(cursor.getColumnIndex("AMOUNT")));
            description.add( cursor.getString(cursor.getColumnIndex("DESCRIPTION")));

        }

        historyList.add(category);
        historyList.add(date);
        historyList.add(amount);
        historyList.add(description);
        cursor.close();
        db.close();

        return historyList;

    }

    public String calculateTotalIncome(String start, String end){
        String data=null;
        Cursor cursor =db.rawQuery("Select sum(AMOUNT)as Total from Income where DATE between '" + start + "' AND '" + end + "'",null);

        if (cursor.moveToFirst()) {
            data = Integer.toString(cursor.getInt(cursor.getColumnIndex("Total")));
        }

        cursor.close();
        //db.close();

        return data;
    }

    public String calculateTotalExpense(String start, String end){
        String data=null;
        Cursor cursor =db.rawQuery("Select sum(AMOUNT) as TotalExpense, count(AMOUNT) as counter from Expense  where DATE between '" + start + "' AND '" + end + "'",null);

        if (cursor.moveToFirst()) {
            data = Integer.toString(cursor.getInt(cursor.getColumnIndex("TotalExpense")));
        }

        cursor.close();
        db.close();

        return data;
    }

    public ArrayList<ArrayList<String>> graphData(String start, String end){
        ArrayList<ArrayList<String>> dataForGraph = new ArrayList<>();
        ArrayList<String> totals=new ArrayList<>();
        ArrayList<String>  categories = new ArrayList<>();

        Cursor cursor =db.rawQuery("Select sum(AMOUNT) as TotalExpense, CATEGORY from Expense  where DATE between '" + start + "' AND '" + end + "' Group By CATEGORY",null);

        while (cursor.moveToNext()) {

            categories.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            totals.add(cursor.getString(cursor.getColumnIndex("TotalExpense")));
        }
        cursor.close();
        db.close();
        dataForGraph.add(categories);
        dataForGraph.add(totals);
        return dataForGraph ;
    }
}
