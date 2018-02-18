package com.example.shanika.expensetracker;

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
    private static final String[] DefaultIncomeCategories= new String[]{"Salary","Sales","Loan","Deposits","Savings"};
    private static final String[] DefaultExpenseCategories= new String[]{"Health","Education","Transportation","Communication","Food","Household","Clothing","Fun","Payments","Internet","Holiday"};


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table Category(CAT_NAME TEXT PRIMARY KEY,TYPE TEXT)");
        for (String category:DefaultIncomeCategories){
            db.execSQL("Create table "+category+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, MONTH TEXT, AMOUNT REAL, DESCRIPTION TEXT)");
            db.execSQL("Insert into Category(CAT_NAME,TYPE) values('"+category+"','Income');");
        }
        for (String category:DefaultExpenseCategories){
            db.execSQL("Create table "+category+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, MONTH TEXT, AMOUNT REAL, DESCRIPTION TEXT)");
            db.execSQL("Insert into Category(CAT_NAME,TYPE) values('"+category+"','Expense');");
         }
        db.execSQL("Create table FrequentData(ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT, AMOUNT REAL, REPEATING_DATE TEXT, DESCRIPTION TEXT)");
        db.execSQL("Create table DailySummary(DATE TEXT PRIMARY KEY, DAILY_INCOME REAL,DAILY_EXPENSE REAL, FORWARDING_REMAINING REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table if exists"+ FREQUENT_DATA_TABLE);
        db.execSQL("Drop Table if exists"+CATEGORY);
        db.execSQL("Drop Table if exists"+DAILY_SUMMARY_TABLE);

        for (String category:DefaultIncomeCategories){
            db.execSQL("Drop Table if exists"+ category);
        }

        for (String category:DefaultExpenseCategories){
            db.execSQL("Drop Table if exists"+ category);
        }
        onCreate(db);

    }
}
