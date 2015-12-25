package be.toron.jdt.ambucheck.test.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.GregorianCalendar;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.activity.MainActivity;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;
import be.toron.jdt.ambucheck.test.testutils.MyTestableDb;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FillOutCheckListActivityTest
{
    @Test
    public void create_ShouldRenderItemsFromCheckList()
    {
        ActivityController<FillOutChecklistActivity> controller = Robolectric.buildActivity(FillOutChecklistActivity.class);
        FillOutChecklistActivity activity = controller.get();

        CheckList checkList = new CheckList();
        checkList.addCheckListItem(new CheckListItem("Foo"));
        checkList.addCheckListItem(new CheckListItem("Bar"));

        activity.setCheckList(checkList);

        controller.create();

        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.checkListItemLayout);

        CheckBox child1 = (CheckBox)layout.getChildAt(0);
        CheckBox child2 = (CheckBox)layout.getChildAt(1);

        assertEquals("Foo", child1.getText());
        assertEquals("Bar", child2.getText());
    }

    @Test
    public void SubmitCheckListShouldSaveCheckListToDatabaseAndReturnToTheMainScreen()
    {
        //arrange
        ActivityController<FillOutChecklistActivity> controller = Robolectric.buildActivity(FillOutChecklistActivity.class);
        FillOutChecklistActivity activity = controller.get();

        CheckList checkList = new CheckList();
        checkList.addCheckListItem(new CheckListItem("Foo"));
        checkList.addCheckListItem(new CheckListItem("Bar"));

        MyTestableDb db = new MyTestableDb();

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2015, 11, 25, 18, 10, 0);

        activity.setCheckList(checkList);
        activity.setDatabase(db);
        activity.setCalendar(calendar);

        controller.create();

        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.checkListItemLayout);
        CheckBox foo = (CheckBox)layout.getChildAt(0);
        foo.setChecked(true);

        TextView completedByName = (TextView) activity.findViewById(R.id.completedBy);
        completedByName.setText("Bunny");

        //act
        Button completeButton = (Button) activity.findViewById(R.id.buttonSubmit);
        completeButton.performClick();

        //assert
        CheckList savedCheckList = db.GetLastSavedCheckList();
        assertThat(savedCheckList.getCompletedBy(), equalTo("Bunny"));
        assertThat(savedCheckList.getCompletedOn(), equalTo(calendar.getTime()));
        assertThat(savedCheckList.getCheckListItems().size(), equalTo(2));
        assertThat(savedCheckList.getCheckListItems().get(0).getDescription(), equalTo("Foo"));
        assertThat(savedCheckList.getCheckListItems().get(0).getChecked(), equalTo(true));
        assertThat(savedCheckList.getCheckListItems().get(1).getDescription(), equalTo("Bar"));
        assertThat(savedCheckList.getCheckListItems().get(1).getChecked(), equalTo(false));

        Intent expectedIntent = new Intent(activity, MainActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity(), IsEqual.equalTo(expectedIntent));
    }
}
