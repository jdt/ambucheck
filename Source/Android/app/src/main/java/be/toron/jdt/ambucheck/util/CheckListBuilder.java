package be.toron.jdt.ambucheck.util;

import android.util.JsonReader;
import android.util.JsonWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import be.toron.jdt.ambucheck.activity.controls.CheckListItemCheckBox;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;

public class CheckListBuilder
{
    public CheckList BuildFrom(String json)
    {
        CheckList result = new CheckList();

        JsonReader reader = new JsonReader(new StringReader(json));
        try
        {
            reader.beginObject();
            while (reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("CheckListItems"))
                {
                    reader.beginArray();
                    while (reader.hasNext())
                    {
                        result.addCheckListItem(readCheckListItem(reader));
                    }
                    reader.endArray();
                }
                else
                {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String SerializeFrom(CheckList checkList)
    {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = new JsonWriter(stringWriter);

        try
        {
            writer.beginObject();

            writer.name("CheckListItems");
            writer.beginArray();
            for(CheckListItem item : checkList.getCheckListItems())
            {
                writer.beginObject();
                writer.name("Description");
                writer.value(item.getDescription());
                writer.name("Checked");
                writer.value(item.getChecked());
                writer.endObject();
            }
            writer.endArray();

            writer.endObject();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        String result = stringWriter.toString();
        return result;
    }

    private CheckListItem readCheckListItem(JsonReader reader) throws IOException
    {
        CheckListItem result = new CheckListItem();

        reader.beginObject();
        while (reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals("Description"))
            {
                result.setDescription(reader.nextString());
            }
            else
            {
                reader.skipValue();
            }
        }
        reader.endObject();

        return result;
    }
}
