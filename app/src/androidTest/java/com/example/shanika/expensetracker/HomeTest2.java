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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest2 {

    @Rule
    public ActivityTestRule<Home> mActivityTestRule = new ActivityTestRule<>(Home.class);

    @Test
    public void homeTest2() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.homeMenu),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Daily"), isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.homeMenu),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Weekly"), isDisplayed()));
        textView2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.homeMenu),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.title), withText("Monthly"), isDisplayed()));
        textView3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.homeMenu),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.title), withText("Yearly"), isDisplayed()));
        textView4.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.income), withContentDescription("Add Income"), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.incomeCancelBtn), withText("CANCEL"),
                        withParent(withId(R.id.buttonsL)),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.expense), withContentDescription("Add Expense"), isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.expenseCancelBtn), withText("CANCEL"),
                        withParent(withId(R.id.buttonsL)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Calculator"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.back),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.relative1)))),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("History"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Search"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatImageButton10 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton10.perform(click());

        ViewInteraction appCompatImageButton11 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton11.perform(click());

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Categories"), isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatImageButton12 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton12.perform(click());

        ViewInteraction appCompatImageButton13 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton13.perform(click());

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Set Limit"), isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatImageButton14 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton14.perform(click());

    }

}
