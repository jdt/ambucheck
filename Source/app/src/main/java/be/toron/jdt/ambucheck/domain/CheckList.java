package be.toron.jdt.ambucheck.domain;

public class CheckList
{
    private DefaultCheckListItemCollection _items;

    public CheckList()
    {
        _items = new DefaultCheckListItemCollection();
    }

    public CheckListItemCollection getCheckListItems()
    {
        return _items;
    }

    public void addCheckListItem(CheckListItem item)
    {
        _items.add(item);
    }
}
