package be.toron.jdt.ambucheck.test.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;

import static org.junit.Assert.assertEquals;

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
}
