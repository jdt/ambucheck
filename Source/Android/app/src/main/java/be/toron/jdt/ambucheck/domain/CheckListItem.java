package be.toron.jdt.ambucheck.domain;

public class CheckListItem
{
    private String _description;
    private boolean _checked;

    public CheckListItem()
    {
        _description = "";
        _checked = false;
    }

    public CheckListItem(String description)
    {
        _description = description;
        _checked = false;
    }

    public String getDescription()
    {
        return _description;
    }

    public void setDescription(String description)
    {
        _description = description;
    }

    public void setChecked(boolean checked)
    {
        _checked = checked;
    }

    public boolean getChecked()
    {
        return _checked;
    }
}
