package be.toron.jdt.ambucheck;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

import be.toron.jdt.ambucheck.activity.FillOutChecklistActivity;
import be.toron.jdt.ambucheck.activity.MainActivity;
import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.db.DefaultDatabase;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.util.CheckListBuilder;
import dagger.Module;
import dagger.Provides;

@Module(injects = {MainActivity.class, FillOutChecklistActivity.class}, library = true)
public class AmbuCheckModule
{
    private Resources _resource;
    private Context _applicationContext;

    public AmbuCheckModule(Resources resources, Context applicationContext)
    {
        _resource = resources;
        _applicationContext = applicationContext;
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

    @Provides
    public Database getDatabase()
    {
        return new DefaultDatabase(new CheckListBuilder(), _applicationContext);
    }

    @Provides
    public Calendar getCalendar()
    {
        return Calendar.getInstance();
    }
}