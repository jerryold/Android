 package edu.ntust.prlab.sessionmanager;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

 @LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest3() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.button_add), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.input_name), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.input_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("s1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.input_age), isDisplayed()));
        appCompatEditText3.perform(replaceText("87"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radio_male),
                        withParent(withId(R.id.radio_gender)),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.button_add), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.input_name), isDisplayed()));
        appCompatEditText4.perform(replaceText("s2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.input_age), isDisplayed()));
        appCompatEditText5.perform(replaceText("36"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.radio_female),
                        withParent(withId(R.id.radio_gender)),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.button_add), isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.input_name), isDisplayed()));
        appCompatEditText6.perform(replaceText("s0"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.input_age), isDisplayed()));
        appCompatEditText7.perform(replaceText("44"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.radio_male),
                        withParent(withId(R.id.radio_gender)),
                        isDisplayed()));
        appCompatRadioButton3.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton3.perform(scrollTo(), click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Sort by Name"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_name), withText("s0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("s0")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_age), withText("44"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText("44")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("♂")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.item_name), withText("s1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("s1")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.item_age), withText("87"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("87")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("♂")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.item_name), withText("s2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("s2")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.item_age), withText("36"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                3),
                        isDisplayed()));
        textView8.check(matches(withText("36")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.item_gender), withText("♀"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                2),
                        isDisplayed()));
        textView9.check(matches(withText("♀")));








        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Sort by Gender"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.item_name), withText("s2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("s2")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.item_age), withText("36"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                3),
                        isDisplayed()));
        textView11.check(matches(withText("36")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.item_gender), withText("♀"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                2),
                        isDisplayed()));
        textView12.check(matches(withText("♀")));


        ViewInteraction textView13 = onView(
                allOf(withId(R.id.item_name), withText("s1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("s1")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.item_age), withText("87"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                3),
                        isDisplayed()));
        textView14.check(matches(withText("87")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                2),
                        isDisplayed()));
        textView15.check(matches(withText("♂")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.item_name), withText("s0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                1),
                        isDisplayed()));
        textView16.check(matches(withText("s0")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.item_age), withText("44"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                3),
                        isDisplayed()));
        textView17.check(matches(withText("44")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                2),
                        isDisplayed()));
        textView18.check(matches(withText("♂")));









        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText("Sort by Age"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.item_name), withText("s2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                1),
                        isDisplayed()));
        textView19.check(matches(withText("s2")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.item_age), withText("36"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                3),
                        isDisplayed()));
        textView20.check(matches(withText("36")));

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.item_gender), withText("♀"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                2),
                        isDisplayed()));
        textView21.check(matches(withText("♀")));

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.item_name), withText("s0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                1),
                        isDisplayed()));
        textView22.check(matches(withText("s0")));

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.item_age), withText("44"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                3),
                        isDisplayed()));
        textView23.check(matches(withText("44")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                2),
                        isDisplayed()));
        textView24.check(matches(withText("♂")));

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.item_name), withText("s1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                1),
                        isDisplayed()));
        textView25.check(matches(withText("s1")));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.item_age), withText("87"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                3),
                        isDisplayed()));
        textView26.check(matches(withText("87")));

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                2),
                        isDisplayed()));
        textView27.check(matches(withText("♂")));













        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.title), withText("Sort by ID"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.item_name), withText("s1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                1),
                        isDisplayed()));
        textView28.check(matches(withText("s1")));

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.item_age), withText("87"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                3),
                        isDisplayed()));
        textView29.check(matches(withText("87")));

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        0),
                                2),
                        isDisplayed()));
        textView30.check(matches(withText("♂")));

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.item_name), withText("s2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                1),
                        isDisplayed()));
        textView31.check(matches(withText("s2")));

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.item_age), withText("36"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                3),
                        isDisplayed()));
        textView32.check(matches(withText("36")));

        ViewInteraction textView33 = onView(
                allOf(withId(R.id.item_gender), withText("♀"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        1),
                                2),
                        isDisplayed()));
        textView33.check(matches(withText("♀")));

        ViewInteraction textView34 = onView(
                allOf(withId(R.id.item_name), withText("s0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                1),
                        isDisplayed()));
        textView34.check(matches(withText("s0")));

        ViewInteraction textView35 = onView(
                allOf(withId(R.id.item_age), withText("44"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                3),
                        isDisplayed()));
        textView35.check(matches(withText("44")));

        ViewInteraction textView36 = onView(
                allOf(withId(R.id.item_gender), withText("♂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view_member),
                                        2),
                                2),
                        isDisplayed()));
        textView36.check(matches(withText("♂")));

        onView(withId(R.id.recycler_view_member)).perform(RecyclerViewActions.actionOnItemAtPosition(0,swipeRight()));
        onView(withId(R.id.recycler_view_member)).perform(RecyclerViewActions.actionOnItemAtPosition(0,swipeRight()));
        onView(withId(R.id.recycler_view_member)).perform(RecyclerViewActions.actionOnItemAtPosition(0,swipeRight()));
        RecyclerView recyclerView = (RecyclerView)this.mActivityTestRule.getActivity().findViewById(R.id.recycler_view_member);
        int count=recyclerView.getLayoutManager().getItemCount();
        assertThat(count, is(0));
    }

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
}
