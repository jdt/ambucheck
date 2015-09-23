package be.toron.jdt.ambucheck;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.util.CheckListBuilder;
import dagger.Module;
import dagger.Provides;

@Module(injects = FillOutChecklistActivity.class, complete = false)
public class AmbuCheckModule
{
    private Resources _resource;

    public AmbuCheckModule(Resources resources)
    {
        _resource = resources;
    }

    @Provides
    public CheckList provideCheckList()
    {
        InputStream resourceReader = _resource.openRawResource(R.raw.checklist);
        Writer writer = new StringWriter();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null)
            {
                writer.write(line);
                line = reader.readLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                resourceReader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        CheckListBuilder builder = new CheckListBuilder();
        return builder.BuildFrom(writer.toString());
    }
}