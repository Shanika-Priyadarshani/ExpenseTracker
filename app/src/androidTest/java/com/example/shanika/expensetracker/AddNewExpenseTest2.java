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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddNewExpenseTest2 {

    @Rule
    public ActivityTestRule<Home> mActivityTestRule = new ActivityTestRule<>(Home.class);

    @Test
    public void addNewExpenseTest2() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.expense), withContentDescription("Add Expense"), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.expenseDateBtn),
                        withParent(withId(R.id.dateArea)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.expenseAmount),
                        withParent(withId(R.id.amountL)),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("500"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.expenseCategorySet),
                        withParent(withId(R.id.categoryL)),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Education"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.expenseAmount), withText("500"),
                        withParent(withId(R.id.amountL)),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.expenseDescription),
                        withParent(withId(R.id.descriptionL)),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Exercise books"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.expenseDescription), withText("Exercise books"),
                        withParent(withId(R.id.descriptionL)),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.expenseAddBtn), withText("ADD"),
                        withParent(withId(R.id.buttonsL)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.expenseCancelBtn), withText("CANCEL"),
                        withParent(withId(R.id.buttonsL)),
                        isDisplayed()));
        appCompatButton3.perform(click());

    }

}
