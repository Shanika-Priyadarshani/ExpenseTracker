package com.example.shanika.expensetracker;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Schema {


    public ArrayList<String> menuSet;
    SQLiteDatabase db;

    public Schema(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<String> getMenuList(String type) {

        menuSet = new ArrayList<>();
        Cursor cursor;

        db.beginTransaction();

        try {
            if (type == "") {
                cursor = db.rawQuery("Select CAT_NAME from Category order by CAT_NAME", null);
            } else {
                cursor = db.rawQuery("Select CAT_NAME from Category where TYPE=? order by CAT_NAME", new String[]{type});
            }
            db.setTransactionSuccessful();

        } finally {

            db.endTransaction();
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

    public ArrayList<ArrayList<String>> getSearchList(String start, String end, String type) {
        ArrayList<ArrayList<String>> searchList = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> amount = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();

        Cursor additional;
        Cursor cursor;

        db.beginTransaction();

        try {
            if (type.equals("All Categories")) {

                cursor = db.rawQuery("Select * from Income where  DATE between ? AND ? UNION Select * from Expense where DATE between ? AND ? order by DATE DESC", new String[]{start, end, start, end});

            } else if (type.equals("All Incomes")) {

                cursor = db.rawQuery("Select * from Income where  DATE between ? AND ? order by DATE DESC", new String[]{start, end});

            } else if (type.equals("All Expenses")) {

                cursor = db.rawQuery("Select * from Expense where DATE between ? AND  ? order by DATE DESC", new String[]{start, end});

            } else {
                additional = db.rawQuery("Select TYPE from Category where CAT_NAME=?", new String[]{type});
                additional.moveToFirst();
                String inEx = additional.getString(additional.getColumnIndex("TYPE"));
                cursor = db.rawQuery("Select * from '" + inEx + "'where Category=? and DATE between ? AND ? order by DATE DESC", new String[]{type, start, end});
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


        while (cursor.moveToNext()) {

            category.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            date.add(cursor.getString(cursor.getColumnIndex("DATE")));
            amount.add(Long.toString(Double.valueOf(cursor.getString(cursor.getColumnIndex("AMOUNT"))).longValue()));
            description.add(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        }

        searchList.add(category);
        searchList.add(date);
        searchList.add(amount);
        searchList.add(description);
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

        db.beginTransaction();

        try {
            cursorIn = db.rawQuery("Select CAT_NAME from Category where TYPE='Income' order by CAT_NAME", null);
            cursorEx = db.rawQuery("Select CAT_NAME from Category where TYPE='Expense' order by CAT_NAME", null);

            db.setTransactionSuccessful();

        } finally {

            db.endTransaction();
        }

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

        db.beginTransaction();

        try {
            if (type.equals("All Categories")) {

                cursor = db.rawQuery("Select * from Income UNION Select * from Expense order by DATE DESC Limit 100", null);

            } else if (type.equals("All Incomes")) {

                cursor = db.rawQuery("Select * from Income order by DATE DESC LIMIT 50", null);

            } else if (type.equals("All Expenses")) {

                cursor = db.rawQuery("Select * from Expense order by DATE DESC  LIMIT 50", null);

            } else {
                additional = db.rawQuery("Select TYPE from Category where CAT_NAME=?", new String[]{type});
                additional.moveToFirst();
                String inEx = additional.getString(additional.getColumnIndex("TYPE"));
                cursor = db.rawQuery("Select * from '" + inEx + "'where Category=? order by DATE DESC LIMIT 30", new String[]{type});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        while (cursor.moveToNext()) {
            category.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            date.add(cursor.getString(cursor.getColumnIndex("DATE")));
            amount.add(Long.toString(Double.valueOf(cursor.getString(cursor.getColumnIndex("AMOUNT"))).longValue()));
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
        Cursor cursor = db.rawQuery("Select sum(AMOUNT)as TotalIncome, count(AMOUNT) as counter from Income where DATE between ? AND ? ", new String[]{start, end});

        if (cursor.moveToFirst()) {
            data = Integer.toString(cursor.getInt(cursor.getColumnIndex("TotalIncome")));
        }

        cursor.close();

        return data;
    }

    public String calculateTotalExpense(String start, String end){
        String data=null;
        Cursor cursor = db.rawQuery("Select sum(AMOUNT) as TotalExpense, count(AMOUNT) as counter from Expense  where DATE between ? AND ?", new String[]{start, end});

        if (cursor.moveToFirst()) {
            data = Integer.toString(cursor.getInt(cursor.getColumnIndex("TotalExpense")));
        }

        cursor.close();
        //db.close();

        return data;
    }

    public ArrayList<ArrayList<String>> graphData(String start, String end){
        ArrayList<ArrayList<String>> dataForGraph = new ArrayList<>();
        ArrayList<String> totals=new ArrayList<>();
        ArrayList<String>  categories = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select sum(AMOUNT) as TotalExpense, CATEGORY from Expense  where DATE between ? AND ? Group By CATEGORY", new String[]{start, end});

        while (cursor.moveToNext()) {

            categories.add(cursor.getString(cursor.getColumnIndex("CATEGORY")));
            totals.add(Long.toString(Double.valueOf(cursor.getString(cursor.getColumnIndex("TotalExpense"))).longValue()));
        }
        cursor.close();
        db.close();
        dataForGraph.add(categories);
        dataForGraph.add(totals);
        return dataForGraph ;
    }
/*
    public double averageExpense(String start, String end,Date d1,Date d2){
        double avgEx= Double.parseDouble(calculateTotalExpense( start,  end))/(numberOfDays(d1,d2));
        return avgEx;

    }

    public double averageIncome(String start, String end,Date d1,Date d2){
        double avgEx= Double.parseDouble(calculateTotalIncome( start,  end))/(numberOfDays(d1,d2));
        return avgEx;

    }

    public int numberOfDays(Date start, Date end){
        long diff=end.getTime()-start.getTime();
        return (int) TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
    }
    */
}
