package be.toron.jdt.ambucheck.db;

import java.util.List;

import be.toron.jdt.ambucheck.domain.CheckList;

public interface Database
{
    void Save(CheckList checkList);
    List<CheckList> SelectCheckLists();

    void UpdateTemplate(CheckList template);
    CheckList GetTemplate();
}
