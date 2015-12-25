package be.toron.jdt.ambucheck.test.testutils;


import java.util.List;

import be.toron.jdt.ambucheck.db.Database;
import be.toron.jdt.ambucheck.domain.CheckList;

public class MyTestableDb implements Database
{
    private List<CheckList> _checkLists;
    private CheckList _lastSavedCheckList;

    @Override
    public void Save(CheckList checkList)
    {
        _lastSavedCheckList = checkList;
    }

    @Override
    public List<CheckList> SelectCheckLists()
    {
        return _checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists)
    {
        _checkLists = checkLists;
    }

    public CheckList GetLastSavedCheckList()
    {
        return _lastSavedCheckList;
    }
}
