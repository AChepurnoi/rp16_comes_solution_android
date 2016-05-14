package com.bionic.td_android.MainWindow.Overview.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.R;

import java.text.SimpleDateFormat;

/**
 * Created by user on 01.05.2016.
 */
public class ShiftsAdapter extends ArrayAdapter<Shift> {




    public ShiftsAdapter(Context context, Shift[] objects) {
        super(context, R.layout.view_spinner_shifts , objects);

    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomvView(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomvView(position,convertView,parent);
    }

    public View getCustomvView(int position, View convertView, ViewGroup parent){

        if(position == 0){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            text.setText("Select shift to edit");
            return convertView;
        }
        Shift item = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_spinner_shifts, parent, false);
        TextView from = (TextView)convertView.findViewById(R.id.view_spinner_from_date);
        TextView to = (TextView)convertView.findViewById(R.id.view_spinner_to_date);
        from.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(item.getStartTime()));
        to.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(item.getEndTime()));
        return convertView;

    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count ;
    }
}
