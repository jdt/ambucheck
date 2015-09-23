package be.toron.jdt.ambucheck.test;

import android.annotation.SuppressLint;

import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import be.toron.jdt.ambucheck.AmbuCheckApplication;
import be.toron.jdt.ambucheck.AmbuCheckModule;
import dagger.ObjectGraph;

public class TestAmbuCheckApplication extends AmbuCheckApplication
        implements TestLifecycleApplication
{
    @Override
    public void beforeTest(Method method)
    {

    }

    @Override
    public void prepareTest(Object test)
    {

    }

    @Override
    public void afterTest(Method method)
    {

    }

    @Override
    public void inject(Object object)
    {
        //this is overridden to stop injection from happening in unittests
    }
}


