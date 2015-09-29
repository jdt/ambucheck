package be.toron.jdt.ambucheck.test.util;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.FileOutputStream;
import java.io.IOException;

import be.toron.jdt.ambucheck.BuildConfig;
import be.toron.jdt.ambucheck.util.CheckListJsonFromLocalStorageProvider;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CheckListJsonFromLocalStorageProviderTest
{
    @Test
    public void GetShouldReadContentsFromFile() throws IOException
    {
        String content = "foo";
        String file = "test.file";

        Context ctx = RuntimeEnvironment.application;
        FileOutputStream stream = ctx.openFileOutput(file, Context.BIND_AUTO_CREATE);
        stream.write(content.getBytes());

        CheckListJsonFromLocalStorageProvider provider = new CheckListJsonFromLocalStorageProvider(RuntimeEnvironment.application, file);

        assertThat(provider.Get(), is(content));
    }
}
