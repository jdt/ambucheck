package be.toron.jdt.ambucheck.util;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;

import be.toron.jdt.ambucheck.AmbuCheckException;

public class CheckListJsonFromLocalStorageProvider
{
    private Context _ctx;
    private String _fileName;

    public CheckListJsonFromLocalStorageProvider(Context ctx, String fileName)
    {
        _ctx = ctx;
        _fileName = fileName;
    }

    public String Get()
    {
        try
        {
            BufferedInputStream input = new BufferedInputStream(_ctx.openFileInput(_fileName));

            byte[] contents = new byte[1024];

            int bytesRead;
            String strFileContents = null;
            while( (bytesRead = input.read(contents)) != -1)
            {
                strFileContents = new String(contents, 0, bytesRead);
            }

            return strFileContents;
        }
        catch(IOException e)
        {
            throw new AmbuCheckException(e);
        }
    }
}
