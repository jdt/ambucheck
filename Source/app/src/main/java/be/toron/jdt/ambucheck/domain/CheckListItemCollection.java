package be.toron.jdt.ambucheck.domain;

public interface CheckListItemCollection extends Iterable<CheckListItem>
{
    public int size();
    public CheckListItem get(int location);
}
