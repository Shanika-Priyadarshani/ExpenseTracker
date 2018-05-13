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
public class CalculatorTest2 {

    @Rule
    public ActivityTestRule<Home> mActivityTestRule = new ActivityTestRule<>(Home.class);

    @Test
    public void calculatorTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Calculator"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button7), withText("7"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button8), withText("8"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button9), withText("9"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.buttonadd), withText("+"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.button4), withText("4"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button5), withText("5"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.button6), withText("6"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.buttonsub), withText("-"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.button1), withText("1"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.button2), withText("2"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.button3), withText("3"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.buttonmul), withText("*"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.button5), withText("5"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.buttondiv), withText("/"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.button1), withText("1"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton15.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.button0), withText("0"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton16.perform(click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.button10), withText("."),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton17.perform(click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.button5), withText("5"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton18.perform(click());

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.buttoneql), withText("="),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton19.perform(click());

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.buttonC), withText("C"),
                        withParent(allOf(withId(R.id.relative1),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton20.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.back),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.relative1)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

    }

}
