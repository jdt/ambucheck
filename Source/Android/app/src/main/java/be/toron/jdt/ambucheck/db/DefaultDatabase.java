package be.toron.jdt.ambucheck.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.util.CheckListBuilder;

public class DefaultDatabase extends SQLiteOpenHelper implements Database
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AmbuCheckDB";

    private CheckListBuilder _builder;

    public DefaultDatabase(CheckListBuilder builder, Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        _builder = builder;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE CheckLists (Id TEXT, Contents TEXT);");

        db.execSQL("CREATE TABLE Settings (Key TEXT, Value TEXT);");
        ContentValues values = new ContentValues();
        values.put("Key", "CheckListTemplate");
        values.put("Value", "{}");
        db.insert("Settings", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    @Override
    public void Save(CheckList checkList)
    {
        String json = _builder.SerializeFrom(checkList);

        ContentValues values = new ContentValues();
        values.put("Id", UUID.randomUUID().toString());
        values.put("Contents", json);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("CheckLists", null, values);
        db.close();
    }

    @Override
    public List<CheckList> SelectCheckLists()
    {
        List<CheckList> checkLists = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("CheckLists", new String[]{"Contents"}, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            checkLists.add(_builder.BuildFrom(cursor.getString(0)));
        }
        cursor.close();
        db.close();

        return checkLists;
    }

    @Override
    public void UpdateTemplate(CheckList template)
    {
        String json = _builder.SerializeFrom(template);

        ContentValues values = new ContentValues();
        values.put("Value", json);

        SQLiteDatabase db = getWritableDatabase();
        db.update("Settings", values, "Key = ?", new String[]{"CheckListTemplate"});
        db.close();
    }

    @Override
    public CheckList GetTemplate()
    {
        CheckList result = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Settings", new String[]{"Value"}, "Key = ?", new String[]{"CheckListTemplate"}, null, null, null);
        if(cursor.moveToNext())
        {
            result = _builder.BuildFrom(cursor.getString(0));
        }
        cursor.close();
        db.close();

        return result;
    }
}