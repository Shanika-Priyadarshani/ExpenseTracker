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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddCategoryTest2 {

    @Rule
    public ActivityTestRule<AddCategory> mActivityTestRule = new ActivityTestRule<>(AddCategory.class);

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
    public void addCategoryTest2() {
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.type),
                        withParent(withId(R.id.typeArea)),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Expense"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.name), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.name), isDisplayed()));
        appCompatEditText2.perform(replaceText("Pets' Care"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.name), withText("Pets' Care"), isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.addCategoryBtn), withText("ADD"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.catgoryCancelBtn), withText("CANCEL"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.add),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.back),
                        withParent(allOf(withId(R.id.topBar),
                                withParent(withId(R.id.whole)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.add),
                        withParent(withId(R.id.topBar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction spinner = onView(
                allOf(withId(R.id.type),
                        childAtPosition(
                                allOf(withId(R.id.typeArea),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.name),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("")));

        ViewInteraction button = onView(
                allOf(withId(R.id.addCategoryBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.whole),
                                        3),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.catgoryCancelBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.whole),
                                        3),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView3),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.whole),
                                        1),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView2), withText("Type"),
                        childAtPosition(
                                allOf(withId(R.id.typeArea),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Type")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView3), withText("Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Name")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.addingArea), withText("Add new Category"),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                withId(R.id.whole),
                                                0)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Add new Category")));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.topBar),
                        childAtPosition(
                                allOf(withId(R.id.whole),
                                        childAtPosition(
                                                withId(R.id.main),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.back),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                withId(R.id.whole),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.back),
                        childAtPosition(
                                allOf(withId(R.id.topBar),
                                        childAtPosition(
                                                withId(R.id.whole),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

    }
}
