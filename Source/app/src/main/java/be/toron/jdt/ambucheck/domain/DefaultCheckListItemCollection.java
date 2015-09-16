package be.toron.jdt.ambucheck.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCheckListItemCollection implements CheckListItemCollection
{
    private List<CheckListItem> _collection;

    public DefaultCheckListItemCollection()
    {
        _collection = new ArrayList<>();
    }

    @Override
    public int size()
    {
        return _collection.size();
    }

    @Override
    public CheckListItem get(int location)
    {
        return _collection.get(location);
    }

    @Override
    public Iterator<CheckListItem> iterator()
    {
        return _collection.iterator();
    }

    public void add(CheckListItem item)
    {
        _collection.add(item);
    }
}
