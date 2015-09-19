package be.toron.jdt.ambucheck.test.activity;

import android.content.Intent;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.activity.MainActivity;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest
{
    @Test
    public void FillOutCheckListButtonClickShouldLaunchFillOutCheckListActivity()
    {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        controller.create();

        MainActivity activity = controller.get();

        activity.findViewById(R.id.buttonFillOut).performClick();

        Intent expectedIntent = new Intent(activity, FillOutChecklistActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity(), equalTo(expectedIntent));

    }
}