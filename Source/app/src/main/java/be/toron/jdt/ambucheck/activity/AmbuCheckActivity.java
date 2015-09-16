package be.toron.jdt.ambucheck.activity;


import android.app.Activity;
import android.os.Bundle;

import be.toron.jdt.ambucheck.AmbuCheckApplication;

public abstract class AmbuCheckActivity extends Activity
{
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Perform injection so that when this call returns all dependencies will be available for use.
        ((AmbuCheckApplication) getApplication()).inject(this);
    }
}