package be.toron.jdt.ambucheck.domain;

import java.util.Date;

public class CheckList
{
    private DefaultCheckListItemCollection _items;
    private Date _completedOn;
    private String _completedBy;

    public CheckList()
    {
        _items = new DefaultCheckListItemCollection();
        _completedOn = null;
        _completedBy = "";
    }

    public CheckListItemCollection getCheckListItems()
    {
        return _items;
    }

    public void addCheckListItem(CheckListItem item)
    {
        _items.add(item);
    }

    public void setCompletedOn(Date on)
    {
        _completedOn = on;
    }

    public Date getCompletedOn()
    {
        return _completedOn;
    }

    public void setCompletedBy(String name)
    {
        _completedBy = name;
    }

    public String getCompletedBy()
    {
        return _completedBy;
    }
}
