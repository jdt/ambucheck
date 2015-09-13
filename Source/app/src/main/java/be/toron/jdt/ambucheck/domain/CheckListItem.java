package be.toron.jdt.ambucheck.domain;

public class CheckListItem
{
    private String _description;

    public CheckListItem()
    {
        _description = "";
    }

    public String getDescription()
    {
        return _description;
    }

    public void setDescription(String description)
    {
        _description = description;
    }
}
