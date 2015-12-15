package be.toron.jdt.ambucheck.activity.controls;

import android.content.Context;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import be.toron.jdt.ambucheck.domain.CheckListItem;

public class CheckListItemCheckBox extends CheckBox implements CompoundButton.OnCheckedChangeListener
{
    private CheckListItem _item;

    public CheckListItemCheckBox(Context context, CheckListItem item)
    {
        super(context);

        _item = item;

        setText(_item.getDescription());
        setTextColor(Color.BLACK);

        this.setOnCheckedChangeListener(this);
    }

    public CheckListItem getCheckListItem()
    {
        return _item;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        _item.setChecked(isChecked);
    }
}
