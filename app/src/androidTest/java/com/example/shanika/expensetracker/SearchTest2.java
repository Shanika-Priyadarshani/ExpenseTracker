package com.example.shanika.expensetracker;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchTest2 {

    @Rule
    public ActivityTestRule<Home> mActivityTestRule = new ActivityTestRule<>(Home.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void searchTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Search"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.searchCatMenu),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.title), withText("All Categories"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.searchCatMenu),
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
                allOf(withId(R.id.searchCatMenu),
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
                withId(R.id.startDateBtn));
        appCompatImageButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                withId(R.id.endDateBtn));
        appCompatImageButton6.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.searchBtn), withText("SEARCH"),
                        withParent(allOf(withId(R.id.Search_button),
                                withParent(withId(R.id.srch_layout))))));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withId(R.id.back),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withId(R.id.nav),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.coordinator_layout)))),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Search"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.back),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.glance), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Search")));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.searchCatMenu),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView24), withText("Search your transactions for any period and any category"),
                        childAtPosition(
                                allOf(withId(R.id.srch_layout),
                                        childAtPosition(
                                                withId(R.id.search_scroll),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Search your transactions for any period and any category")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView11), withText("Start Date :"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Start Date :")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.startDateView), withText("2018-05-13"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("2018-05-13")));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.startDateBtn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textView12), withText("End Date  :"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("End Date  :")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.endDateView), withText("2018-05-13"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("2018-05-13")));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.endDateBtn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                2),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.searchBtn),
                        childAtPosition(
                                allOf(withId(R.id.Search_button),
                                        childAtPosition(
                                                withId(R.id.srch_layout),
                                                2)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.searchBtn),
                        childAtPosition(
                                allOf(withId(R.id.Search_button),
                                        childAtPosition(
                                                withId(R.id.srch_layout),
                                                2)),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

    }
}
