package be.toron.jdt.ambucheck.db;

import be.toron.jdt.ambucheck.domain.CheckList;

/**
 * Created by jdt on 15/12/2015.
 */
public interface Database
{
    void Save(CheckList checkList);
}
