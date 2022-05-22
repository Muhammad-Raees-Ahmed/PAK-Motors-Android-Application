package com.example.shopping.ApplicationProgrammingInterface;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopping.R;

import java.util.ArrayList;

public class Adapter_car extends ArrayAdapter {
    public Adapter_car(Activity context, ArrayList<DATA_Car> android) {

        super(context, 0, android);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_car, parent, false);
        }


        DATA_Car currentdata = (DATA_Car) getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.company);
        name.setText(currentdata.getCompany());


        TextView dateTextView = (TextView) listItemView.findViewById(R.id.type);
        dateTextView.setText(currentdata.getCar_type());


        return listItemView;
    }
}
