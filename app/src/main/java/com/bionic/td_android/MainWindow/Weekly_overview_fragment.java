package com.bionic.td_android.MainWindow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bionic.td_android.R;

/**
 * Created by user on 29.03.2016.
 */
public class Weekly_overview_fragment extends Fragment {


    private TableAdapter adapter ;

    public class TableAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public TableAdapter(Context context, String[] values) {
            super(context, R.layout.list_adapter_grid, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_adapter_grid, parent, false);
            TextView week = (TextView) rowView.findViewById(R.id.week_view);
            TextView regularPayment = (TextView) rowView.findViewById(R.id.regular_payments_view);
            TextView additionalPayments = (TextView) rowView.findViewById(R.id.additional_payments_view);
            week.setText(String.valueOf(position + 1));
            regularPayment.setText(values[position]);
            additionalPayments.setText("0");

            if(position == values.length - 1) {
                LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.list_adapter_container_views);
                layout.setPadding(0,10,0,50);

            }

            return rowView;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_weekly_tab,container,false);
        ListView listView = (ListView) view.findViewById(R.id.overview_list_view);
        adapter = new TableAdapter(view.getContext(),new String[]{"40","30","20","30","40","30","40","30","40"
                ,"20","30","25","34","54","45","0"});
        listView.setAdapter(adapter);
        return view;
    }
}
