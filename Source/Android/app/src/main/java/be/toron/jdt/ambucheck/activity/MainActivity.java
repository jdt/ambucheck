package be.toron.jdt.ambucheck.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import javax.inject.Inject;

import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.services.UpdateCheckListService;
import be.toron.jdt.ambucheck.view.CheckListAdapter;


public class MainActivity extends AmbuCheckActivity {

    @Inject
    Database _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listViewCompleted);
        listView.setAdapter(new CheckListAdapter(this, _db.SelectCheckLists()));

        Intent intent = new Intent(this, UpdateCheckListService.class);
        startService(intent);
    }

    public void fillOutChecklist(View view) {
        Intent intent = new Intent(this, FillOutChecklistActivity.class);
        startActivity(intent);
    }

    public void setDatabase(Database db)
    {
        _db = db;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
