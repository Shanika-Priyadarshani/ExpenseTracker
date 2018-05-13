package com.example.shanika.expensetracker;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HistoryTest2 {

    @Rule
    public ActivityTestRule<Home> mActivityTestRule = new ActivityTestRule<>(Home.class);

    @Test
    public void historyTest23() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("History"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.historyType),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.title), withText("All Categories"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.historyType),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.title), withText("Income"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.title), withText("All Incomes"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.historyType),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.title), withText("Expense"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(android.R.id.title), withText("All Expenses"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

    }

}
