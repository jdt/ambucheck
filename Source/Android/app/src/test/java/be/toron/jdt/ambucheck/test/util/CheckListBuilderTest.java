package be.toron.jdt.ambucheck.test.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;
import be.toron.jdt.ambucheck.util.CheckListBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
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
        CheckList result = _builder.BuildFrom("{\"CheckListItems\": [{\"Description\": \"Test1\"},{\"Description\": \"Test2\"}]}");

        assertThat(result.getCheckListItems().size(), is(2));

        CheckListItem item1 = result.getCheckListItems().get(0);
        assertThat(item1.getDescription(), is("Test1"));

        CheckListItem item2 = result.getCheckListItems().get(1);
        assertThat(item2.getDescription(), is("Test2"));
    }
}
