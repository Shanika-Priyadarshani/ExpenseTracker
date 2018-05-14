package com.example.shanika.expensetracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() throws Exception {
        databaseHelper = new DatabaseHelper(getTargetContext());
        database = databaseHelper.getWritableDatabase();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void insertCategory() throws Exception {
        databaseHelper.insertCategory("Bursary", "Income");
        ArrayList<ArrayList<String>> CategoryList = new Schema(getTargetContext()).getCategoryList();
        assertThat(CategoryList.get(0).size(), is(6));
        assertTrue(CategoryList.get(0).get(0).equals("Bursary"));
    }

    @Test
    public void insertIncomeRecord() throws Exception {
        databaseHelper.insertIncomeRecord("2018-05-25", "Sales", 5000.00, "Car Sales");
        ArrayList<ArrayList<String>> historyList = new Schema(getTargetContext()).getHistoryList("Sales");
        assertTrue(historyList.get(0).get(0).equals("Sales"));
        assertTrue(historyList.get(1).get(0).equals("2018-05-25"));
        assertTrue(historyList.get(2).get(0).equals("5000"));
        assertTrue(historyList.get(3).get(0).equals("Car Sales"));

    }

    @Test
    public void insertExpenseRecord() throws Exception {
        databaseHelper.insertExpenseRecord("2018-05-15", "Education", 5000.00, "Study Materials");
        ArrayList<ArrayList<String>> historyList = new Schema(getTargetContext()).getHistoryList("Education");
        assertTrue(historyList.get(0).get(0).equals("Education"));
        assertTrue(historyList.get(1).get(0).equals("2018-05-15"));
        assertTrue(historyList.get(2).get(0).equals("5000"));
        assertTrue(historyList.get(3).get(0).equals("Study Materials"));
    }

    @Test
    public void insertFrequentRecord() throws Exception {

    }

    @Test
    public void insertDailySummary() throws Exception {

    }

}