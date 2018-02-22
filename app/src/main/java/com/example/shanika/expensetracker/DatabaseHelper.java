package com.example.shanika.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;


/**
 * Created by Shanika on 2/13/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="ExpenseTracker.db";

    private static final String CATEGORY ="Category";
    private static final String FREQUENT_DATA_TABLE ="FrequentData";
    private static final String DAILY_SUMMARY_TABLE ="DailySummary";
    private static final String[] DefaultCategories= new String[]{"Income","Expense"};

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table Category(CAT_NAME TEXT PRIMARY KEY,TYPE TEXT)");
        for (String category:DefaultCategories){
            db.execSQL("Create table "+category+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, CATEGORY TEXT, AMOUNT REAL, DESCRIPTION TEXT)");

        }

        db.execSQL("Create table FrequentData(ID INTEGER PRIMARY KEY AUTOINCREMENT,REPEAT TEXT,CATEGORY TEXT, AMOUNT REAL, REPEATING_DATE TEXT, DESCRIPTION TEXT)");
        db.execSQL("Create table DailySummary(DATE TEXT PRIMARY KEY, DAILY_INCOME REAL,DAILY_EXPENSE REAL, FORWARDING_REMAINING REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table if exists"+ FREQUENT_DATA_TABLE);
        db.execSQL("Drop Table if exists"+CATEGORY);
        db.execSQL("Drop Table if exists"+DAILY_SUMMARY_TABLE);

        for (String category:DefaultCategories){
            db.execSQL("Drop Table if exists"+ category);
        }

        onCreate(db);

    }

    public boolean insertCategory(String name, String type){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues conv= new ContentValues();
        conv.put("cat_name",name);
        conv.put("type",type);
        long result=db.insert("Category",null,conv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean insertIncomeRecord(String date, String category, Double amount, String description ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues conv= new ContentValues();
        conv.put("date",date);
        conv.put("category",category);
        conv.put("amount",amount);
        conv.put("description",description);
        long result=db.insert("Income",null,conv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertExpenseRecord(String date, String category, Double amount, String description ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues conv= new ContentValues();
        conv.put("date",date);
        conv.put("category",category);
        conv.put("amount",amount);
        conv.put("description",description);
        long result=db.insert("Expense",null,conv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertFrequentRecord(String repeat, String category, Double amount,String repeating_date, String description ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues conv= new ContentValues();
        conv.put("repeat",repeat);
        conv.put("category",category);
        conv.put("amount",amount);
        conv.put("repeating_date",repeating_date);
        conv.put("description",description);
        long result=db.insert("FrequentData",null,conv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertDailySummary(String date, Double daily_income, Double daily_expense, Double forwarding_remaining ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues conv= new ContentValues();
        conv.put("date",date);
        conv.put("daily_income",daily_income);
        conv.put("daily_expense",daily_expense);
        conv.put("forwarding_remaining",forwarding_remaining);
        long result=db.insert("DailySummmary",null,conv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

}
