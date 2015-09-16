package be.toron.jdt.ambucheck;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class AmbuCheckApplication extends Application
{
    private ObjectGraph graph;

    @Override public void onCreate()
    {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules()
    {
        List<Object> result = new ArrayList<>();

        result.add(new AmbuCheckModule(getResources()));

        return result;
    }

    public void inject(Object object)
    {
        graph.inject(object);
    }
}