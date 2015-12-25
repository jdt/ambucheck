package be.toron.jdt.ambucheck.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import javax.inject.Inject;

import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.activity.controls.CheckListItemCheckBox;
import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;


public class FillOutChecklistActivity extends AmbuCheckActivity
{
    @Inject
    CheckList _checkList;
    @Inject
    Database _db;
    @Inject
    Calendar _calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_out_checklist);

        LinearLayout layout = (LinearLayout) findViewById(R.id.checkListItemLayout);
        for(CheckListItem item : _checkList.getCheckListItems())
        {
            CheckListItemCheckBox box = new CheckListItemCheckBox(getBaseContext(), item);
            layout.addView(box);
        }
    }

    public void setCheckList(CheckList checkList)
    {
        _checkList = checkList;
    }

    public void setDatabase(Database database)
    {
        _db = database;
    }

    public void setCalendar(Calendar calendar)
    {
        _calendar = calendar;
    }

    public void submitCheckList(View view)
    {
        CheckList listToSubmit = new CheckList();

        LinearLayout layout = (LinearLayout) findViewById(R.id.checkListItemLayout);
        for(int i = 0; i < layout.getChildCount(); i++)
        {
            CheckListItemCheckBox box = (CheckListItemCheckBox) layout.getChildAt(i);
            listToSubmit.addCheckListItem(box.getCheckListItem());
        }

        TextView textPersonName = (TextView)findViewById(R.id.completedBy);
        listToSubmit.setCompletedBy(textPersonName.getText().toString());
        listToSubmit.setCompletedOn(_calendar.getTime());

        _db.Save(listToSubmit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fill_out_checklist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
