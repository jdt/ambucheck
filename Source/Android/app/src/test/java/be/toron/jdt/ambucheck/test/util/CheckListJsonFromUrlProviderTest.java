package be.toron.jdt.ambucheck.test.util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.net.MalformedURLException;
import java.net.URL;

import be.toron.jdt.ambucheck.AmbuCheckException;
import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.util.CheckListJsonFromUrlProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CheckListJsonFromUrlProviderTest
{
    private CheckListJsonFromUrlProvider _provider;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void SetUp() throws MalformedURLException
    {
        _provider = new CheckListJsonFromUrlProvider(new URL("http://localhost/sandbox.json"));
    }

    @Test
    public void TestBuildFrom()
    {
        String result = _provider.Get();

        assertThat(result, is("{\t\"CheckListItems\":\t[\t\t{\t\t\t\"Description\": \"Test1\"\t\t},\t\t{\t\t\t\"Description\": \"Test2\"\t\t}\t]}"));
    }

    @Test
    public void TestBuildFromInvalidUrlShouldThrowException() throws MalformedURLException
    {
        _provider = new CheckListJsonFromUrlProvider(new URL("http://localhost/nonexistingfile.json"));

        expectedException.expect(AmbuCheckException.class);
        expectedException.expectMessage("Unexpected response when trying to get JSON from URL: [404]Not Found");

        _provider.Get();
    }
}
