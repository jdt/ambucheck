package be.toron.jdt.ambucheck.test.util;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Calendar;
import java.util.GregorianCalendar;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;
import be.toron.jdt.ambucheck.util.CheckListBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CheckListBuilderTest
{
    private CheckListBuilder _builder;

    @Before
    public void SetUp()
    {
        _builder = new CheckListBuilder();
    }

	@Test
    public void TestBuildFrom()
    {
        CheckList result = _builder.BuildFrom("{\"CheckListItems\": [{\"Description\": \"Test1\", \"Checked\": true},{\"Description\": \"Test2\", \"Checked\": false}], \"CompletedBy\": \"foo\", \"CompletedOn\": \"20151220150235\"}");

        Calendar cal = new GregorianCalendar(2015, 11, 20, 15, 02, 35);

        assertThat(result.getCompletedBy(), is("foo"));
        assertThat(result.getCompletedOn(), is(cal.getTime()));
        assertThat(result.getCheckListItems().size(), is(2));

        CheckListItem item1 = result.getCheckListItems().get(0);
        assertThat(item1.getDescription(), is("Test1"));
        assertThat(item1.getChecked(), is(true));

        CheckListItem item2 = result.getCheckListItems().get(1);
        assertThat(item2.getDescription(), is("Test2"));
        assertThat(item2.getChecked(), is(false));
    }

    @Test
    public void TestBuildFromShouldBuildFromJsonWithOnlyCheckListItems()
    {
        CheckList result = _builder.BuildFrom("{\"CheckListItems\": [{\"Description\": \"Test1\"},{\"Description\": \"Test2\"}]}");

        assertThat(result.getCompletedBy(), is(""));
        assertThat(result.getCompletedOn(), is(nullValue()));
        assertThat(result.getCheckListItems().size(), is(2));

        CheckListItem item1 = result.getCheckListItems().get(0);
        assertThat(item1.getDescription(), is("Test1"));
        assertThat(item1.getChecked(), is(false));

        CheckListItem item2 = result.getCheckListItems().get(1);
        assertThat(item2.getDescription(), is("Test2"));
        assertThat(item2.getChecked(), is(false));
    }

    @Test
    public void TestSerializeFrom()
    {
        Calendar cal = new GregorianCalendar(2015, 11, 20, 15, 02, 35);

        CheckList checkList = new CheckList();
        checkList.setCompletedBy("foo");
        checkList.setCompletedOn(cal.getTime());

        CheckListItem item1 = new CheckListItem("Item 1");
        item1.setChecked(true);

        CheckListItem item2 = new CheckListItem("Item 2");
        item2.setChecked(false);

        checkList.addCheckListItem(item1);
        checkList.addCheckListItem(item2);

        String result = _builder.SerializeFrom(checkList);

        assertThat(result, is("{\"CheckListItems\":[{\"Description\":\"Item 1\",\"Checked\":true},{\"Description\":\"Item 2\",\"Checked\":false}],\"CompletedBy\":\"foo\",\"CompletedOn\":\"20151220150235\"}"));
    }
}
