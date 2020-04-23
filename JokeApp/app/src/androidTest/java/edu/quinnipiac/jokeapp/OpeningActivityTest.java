package edu.quinnipiac.ser210.jokeapplication;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class OpeningActivityTest {
    @Rule
    // public ActivityTestRule<OpeningActivity> openingActivityActivityTestRule =
    // new ActivityTestRule<OpeningActivity>(OpeningActivity.class);
    private OpeningActivity openingActivity = null;

    @Before
    public void setUp() throws Exception {
        //openingActivity = openingActivityActivityTestRule.getActivity();
        fail("Not yet implemented");
    }

    @Test
    public void onCreate() {
        fail("Not yet implemented");
    }

    @Test
    public void onBegin() {
        /*final Button begin = openingActivity.findViewById(R.id.begin);

        openingActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                begin.performClick();
            }
        });

        assertTrue(openingActivity.sc.begin); */
        fail("Not yet implemented");
    }
}