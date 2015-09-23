package be.toron.jdt.ambucheck.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import javax.inject.Inject;

import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.domain.CheckList;
import be.toron.jdt.ambucheck.domain.CheckListItem;


public class FillOutChecklistActivity extends AmbuCheckActivity
{
    @Inject
    CheckList _checkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_out_checklist);

        LinearLayout layout = (LinearLayout) findViewById(R.id.checkListItemLayout);
        for(CheckListItem item : _checkList.getCheckListItems())
        {
            CheckBox box = new CheckBox(getBaseContext());
            box.setText(item.getDescription());

            layout.addView(box);
        }
    }

    public void setCheckList(CheckList checkList)
    {
        _checkList = checkList;
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
