package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by vijay on 2/16/19.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test(){
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            EndpointsAsyncTask eat = new EndpointsAsyncTask();
            eat.response = new EndpointsAsyncTask.TaskResponse() {
                @Override
                public void process(String out) {
                    assertNotNull(out);
                    signal.countDown();
                }
            };
            eat.execute(new Pair<Context, String>(mActivityTestRule.getActivity(), "Manfred"));
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
