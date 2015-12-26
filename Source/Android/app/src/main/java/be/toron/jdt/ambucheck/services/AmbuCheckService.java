package be.toron.jdt.ambucheck.services;

import android.app.IntentService;

import be.toron.jdt.ambucheck.AmbuCheckApplication;

public abstract class AmbuCheckService extends IntentService
{
    protected AmbuCheckService(String name)
    {
        super(name);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        ((AmbuCheckApplication) getApplication()).inject(this);
    }
}
