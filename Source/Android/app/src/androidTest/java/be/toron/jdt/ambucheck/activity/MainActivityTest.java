package be.toron.jdt.ambucheck.activity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.activity.MainActivity;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent _launchIntent;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
        protected void setUp() throws Exception {
            super.setUp();
        _launchIntent = new Intent(getInstrumentation()
                    .getTargetContext(), MainActivity.class);
        }

    @MediumTest
    public void testFillOutClickShouldLaunch() {
        startActivity(_launchIntent, null, null);
        final Button fillOutButton = (Button) getActivity().findViewById(R.id.buttonFillOut);
        fillOutButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertEquals(FillOutChecklistActivity.class.getName(), launchIntent.getComponent().getClassName());
    }
}

