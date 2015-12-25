package be.toron.jdt.ambucheck.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import be.toron.jdt.ambucheck.R;
import be.toron.jdt.ambucheck.domain.CheckList;

public class CheckListAdapter extends BaseAdapter
{
    private List<CheckList> _checkLists;
    private static LayoutInflater inflater=null;

    public CheckListAdapter(Activity parentActivity, List<CheckList> checkLists)
    {
        _checkLists = checkLists;
        inflater = ( LayoutInflater )parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return _checkLists.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.list_completedchecklistst, null);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        TextView completedOn = (TextView) rowView.findViewById(R.id.checkListCompletedOn);
        completedOn.setText(format.format(_checkLists.get(position).getCompletedOn()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rowView;
    }

    public List<CheckList> getCheckLists()
    {
        return _checkLists;
    }
}
