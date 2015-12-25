package be.toron.jdt.ambucheck.test.activity;

import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.activity.MainActivity;
import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.services.UpdateCheckListService;
import be.toron.jdt.ambucheck.test.testutils.MyTestableDb;
import be.toron.jdt.ambucheck.view.CheckListAdapter;

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

    @Test
    public void CreatingMainActivityShouldDisplayCheckListsInDatabaseAndCheckForUpdatedCheckList()
    {
        //arrange
        CheckList list1 = new CheckList();
        list1.setCompletedOn(new GregorianCalendar(2015, 11, 18, 13, 35, 53).getTime());

        CheckList list2 = new CheckList();
        list2.setCompletedOn(new GregorianCalendar(2015, 11, 20, 15, 02, 35).getTime());

        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(list1);
        checkLists.add(list2);

        MyTestableDb db = new MyTestableDb();
        db.setCheckLists(checkLists);

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity activity = controller.get();
        activity.setDatabase(db);

        //act
        controller.create();

        //assert
        ListView listView = (ListView) activity.findViewById(R.id.listViewCompleted);
        CheckListAdapter adapter = (CheckListAdapter) listView.getAdapter();
        assertThat(adapter.getCheckLists(), equalTo(checkLists));

        Intent expectedIntent = new Intent(activity, UpdateCheckListService.class);
        assertThat(shadowOf(activity).getNextStartedService(), equalTo(expectedIntent));
    }
}