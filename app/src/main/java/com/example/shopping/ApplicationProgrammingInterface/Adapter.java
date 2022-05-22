package com.example.shopping.ApplicationProgrammingInterface;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shopping.R;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    public Adapter(Activity context, ArrayList<DATA> android) {

        super(context, 0, android);
    }


        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.items, parent, false);
            }


            DATA currentdata = (DATA) getItem(position);

            TextView name = (TextView) listItemView.findViewById(R.id.name);
            name.setText(currentdata.getBname());


            TextView dateTextView = (TextView) listItemView.findViewById(R.id.countryname);
            dateTextView.setText(currentdata.getCname());

            TextView dateTextView1 = (TextView) listItemView.findViewById(R.id.cdate);
            dateTextView1.setText(currentdata.getOdate());

            return listItemView;
    }
}
