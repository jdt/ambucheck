package be.toron.jdt.ambucheck.services;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import be.toron.jdt.ambucheck.AmbuCheckException;
import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.util.CheckListBuilder;

public class UpdateCheckListService extends AmbuCheckService
{
    @Inject
    Database _db;
    @Inject
    CheckListBuilder _builder;

    public UpdateCheckListService()
    {
        super("UpdateCheckListService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        HttpURLConnection c = null;
        try
        {
            URL url = new URL("http://10.0.2.2/checklist.json");

            c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.connect();
            int status = c.getResponseCode();

            switch (status)
            {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line);
                    }
                    br.close();

                    CheckList template = _builder.BuildFrom(sb.toString());
                    _db.UpdateTemplate(template);
                    break;
                default:
                    throw new AmbuCheckException(String.format("Unexpected response when trying to get JSON from URL: [%d]%s", status, c.getResponseMessage()));
            }

        }
        catch (MalformedURLException e)
        {
            throw new AmbuCheckException(e);
        }
        catch (IOException e)
        {
            throw new AmbuCheckException(e);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (Exception ex)
                {

                }
            }
        }
    }
}
