package com.aaavs.myapplication;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aaavs on 1/8/2018.
 */
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActiivty = null;

    @Before
    public void setUp() throws Exception {
        mActiivty = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()    {
        View view = mActiivty.findViewById(R.id.etTotalBill);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
    }

}