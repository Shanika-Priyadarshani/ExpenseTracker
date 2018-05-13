package com.example.shanika.expensetracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;


public class Home extends AppCompatActivity {

    DatabaseHelper myDB;
    View.OnTouchListener gestureListner;
    private ImageButton btn_nav;
    private ImageButton back;
    private NavigationView navigation;
    private float x1, x2, y1, y2;
    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
       /* ScrollView scrollView= (ScrollView)findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(gestureListner);
        initialize();

        gestureDetector = new GestureDetector(new SwipeGestureDetector());
        gestureListner = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        */
        TextView tx2 = (TextView) findViewById(R.id.overall);
        Functions fn = new Functions();
        String curMon = fn.getCurrentMonth();
        tx2.setText("Monthly overview " + curMon);
        ArrayList<String> dates = new Functions().getDateRange();
        displayAll(dates);
        myDB = new DatabaseHelper(this);


        onClickButtonNav();
        onClickBack();
        onHomemenuClicked();


        TextView tx = (TextView) findViewById(R.id.glance);
        font(tx, "fonts/wet pet.ttf");
        font(tx2, "fonts/MING.ttf");
        TextView tx4 = (TextView) findViewById(R.id.in);
        TextView tx5 = (TextView) findViewById(R.id.re);
        TextView tx6 = (TextView) findViewById(R.id.ex);
        TextView tx7 = (TextView) findViewById(R.id.bal);
        font(tx4, "fonts/Arturo-Bold Trial.ttf");
        font(tx5, "fonts/Arturo-Bold Trial.ttf");
        font(tx6, "fonts/Arturo-Bold Trial.ttf");
        font(tx7, "fonts/Arturo-Bold Trial.ttf");


    }

    public void font(TextView tx, String s) {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), s);
        tx.setTypeface(custom_font);
    }

    public void onClickButtonNav() {
        btn_nav = (ImageButton) findViewById(R.id.nav);
        back = (ImageButton) findViewById(R.id.back);
        btn_nav.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigation = (NavigationView) findViewById(R.id.navigation);
                        navigation.setVisibility(View.VISIBLE);
                        btn_nav.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                    }
                }

        );

    }

    private void onNavigationLeave() {
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setVisibility(View.INVISIBLE);
        btn_nav.setVisibility(View.VISIBLE);
        back.setVisibility(View.INVISIBLE);
    }

    public void onClickBack() {
        btn_nav = (ImageButton) findViewById(R.id.nav);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNavigationLeave();
                    }
                }

        );
    }

    public void onHomemenuClicked() {
        ImageButton homeMenu = (ImageButton) findViewById(R.id.homeMenu);
        homeMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(getApplicationContext(), v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.time_periods, popup.getMenu());
                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                TextView overall = (TextView) findViewById(R.id.overall);

                                if (item.getTitle().equals("Daily")) {
                                    overall.setText("Daily overview");
                                    Functions fn = new Functions();
                                    String start = fn.getToday();
                                    String end = fn.getToday();
                                    onDates(start, end);
                                    PieChart pieChart = (PieChart) findViewById(R.id.piechart);
                                    new Functions().displayGraph(start, end, pieChart, getApplicationContext());
                                } else if (item.getTitle().equals("Weekly")) {
                                    overall.setText("Weekly overview");
                                    ArrayList<String> dates = new Functions().getCurrentWeek();
                                    displayAll(dates);


                                } else if (item.getTitle().equals("Monthly")) {
                                    Functions fn = new Functions();
                                    String curMon = fn.getCurrentMonth();
                                    overall.setText("Monthly overview - " + curMon);
                                    ArrayList<String> dates = fn.getDateRange();
                                    displayAll(dates);

                                } else if (item.getTitle().equals("Yearly")) {
                                    overall.setText("Yearly overview");
                                    ArrayList<String> dates = new Functions().getCurrentYear();
                                    displayAll(dates);

                                }

                                return false;
                            }
                        });
                    }
                }

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidebar_navigation, menu);
        getMenuInflater().inflate(R.menu.navigation_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.income) {
            return true;
        }
        if (id == R.id.calculator) {
            return true;
        }

        if (id == R.id.expense) {
            return true;
        }

        if (id == R.id.search) {
            return true;
        }
        if (id == R.id.history) {
            return true;
        }

        if (id == R.id.limit) {
            return true;
        }

        if (id == R.id.frequent) {
            return true;
        }

        if (id == R.id.categories) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void searchButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Search.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void historyButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, History.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();


    }

    public void categoriesButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Categories.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void limitButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, SetLimit.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void frequentButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, FrequentRecordDisplay.class);
        this.startActivity(intent);
        onNavigationLeave();
        finish();

    }

    public void AddIncomeButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, AddIncomeRecord.class);
        this.startActivity(intent);
        finish();

    }

    public void AddExpenseButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, AddExpenseRecord.class);
        this.startActivity(intent);
        finish();


    }

    public void CalculatorButtonClicked(MenuItem item) {
        Intent intent = new Intent(this, Calculator.class);
        this.startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        CustomDialogClass cdd = new CustomDialogClass(this);
        cdd.show();
    }

    public void onDates(String start, String end) {
        Schema sh = new Schema(getApplicationContext());
        TextView income = (TextView) findViewById(R.id.home_income);
        TextView expense = (TextView) findViewById(R.id.home_expense);
        String totalIncome = sh.calculateTotalIncome(start, end);
        String totalExpense = sh.calculateTotalExpense(start, end);
        income.setText(String.format(" : Rs. %s", totalIncome));
        expense.setText(String.format(" : Rs. %s", totalExpense));

    }

    public void displayAll(ArrayList<String> dates) {
        String start = dates.get(0);
        String end = dates.get(1);
        onDates(start, end);
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        new Functions().displayGraph(start, end, pieChart, getApplicationContext());


    }


 /*   public void displayGraph(String start, String end, PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        Schema sh = new Schema(getApplicationContext());
        ArrayList<ArrayList<String>> dataForGraph = sh.graphData(start, end);
        ArrayList<String> categories = dataForGraph.get(0);
        ArrayList<String> totals = dataForGraph.get(1);

        for (String s : categories) {
            yValues.add(new PieEntry(Float.parseFloat(totals.get(categories.indexOf(s))), s));
        }

        PieDataSet dataSet = new PieDataSet(yValues, "Expenses");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);

        pieChart.setData(data);
        pieChart.invalidate();

    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /*@Override
    public void onClick(View v) {

    }

    private void initialize(){

    }

    private void onLeftSwipe(){
        Intent intent = new Intent(Home.this,History.class);
        startActivity(intent);
    }

    private void onRightSwipe(){
        Intent intent = new Intent(Home.this,Search.class);
        startActivity(intent);
    }



    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_MIN_DISTANCE = 50;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                Toast t = Toast.makeText(Home.this, "Gesture detected", Toast.LENGTH_SHORT);
                t.show();
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Home.this.onLeftSwipe();
                }
                // Right swipe
                else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Home.this.onRightSwipe();
                }
            } catch (Exception e) {
                Log.e("Home", "Error on gestures");
            }
            return false;
        }

    }
*/

    // declare event for motion gesture
   /* public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            // when user first touch the screen get x and y cooordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            }

            case MotionEvent.ACTION_UP: {
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                // if left to right sweep event on screen
                if (x1 < x2) {
                    Intent intent = new Intent(Home.this, Search.class);
                    startActivity(intent);
                }
                // if right to left sweep on screen
                if (x1 > x2) {
                    Intent intent = new Intent(Home.this, History.class);
                    startActivity(intent);
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void sweepAction(){
        ScrollView scrollView =(ScrollView)findViewById(R.id.scrollView);
        scrollView.setOnTouchListener();
    }*/

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e2.getX() > e1.getX()) {
                Intent intent = new Intent(Home.this, Search.class);
                finish();
                startActivity(intent);

            }

            if (e1.getX() < e2.getX()) {
                Intent intent = new Intent(Home.this, History.class);
                finish();
                startActivity(intent);
            }
            return true;
        }
    }


}


