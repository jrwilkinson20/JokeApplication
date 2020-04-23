package edu.quinnipiac.ser210.jokeapplication;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CategoryActivityTest {
    @Rule
    // public ActivityTestRule<CategoryActivity> categoryActivityActivityTestRule =
    // new ActivityTestRule<CategoryActivity>(CategoryActivity.class);
    private CategoryActivity categoryActivity = null;


    @Before
    public void setUp() throws Exception {
        //CategoryActivity = categoryActivityActivityTestRule.getActivity();
    }

    @Test
    public void onCreate() {

    }

    @Test
    public void onCreateOptionsMenu() {
        fail("Not yet implemented");
    }

    @Test
    public void onOptionsItemSelected() {
        fail("Not yet implemented");
    }

    @Test
    public void onClick() {
        /*final Button category = categoryActivity.findViewById(R.id.category);

        categoryActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                category.performClick();
            }
        });

        assertTrue(categoryActivity.sc.category); */
        fail("Not yet implemented");
    }

    @Test
    public void onUserInteraction() {
        fail("Not yet implemented");
    }
}