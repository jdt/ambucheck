package be.toron.jdt.ambucheck.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import be.toron.jdt.ambucheck.AmbuCheckException;

public class CheckListJsonFromUrlProvider
{
    private URL _url;

    public CheckListJsonFromUrlProvider(URL url)
    {
        _url = url;
    }

    public String Get()
    {
        HttpURLConnection c = null;
        try
        {
            c = (HttpURLConnection) _url.openConnection();
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
                    return sb.toString();
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
