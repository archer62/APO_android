package com.example.biryuk.apo_app_and;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Biryuk on 16.08.17.
 */

public class SchoolsSpinnerAdapter extends ArrayAdapter<School> {
    public SchoolsSpinnerAdapter(Context context, List<School> products) {
        super(context, R.layout.fragment_info, products);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row = convertView;


        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.dialog_schools_spinner_view, parent, false);
            holder = new ViewHolder();
            holder.productName = (TextView) row.findViewById(R.id.spinnertextView);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }
        School product = getItem(position);

        holder.productName.setText(product.school_name);

        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row = convertView;


        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.dialog_schools_spinner_view, parent, false);
            holder = new ViewHolder();
            holder.productName = (TextView) row.findViewById(R.id.spinnertextView);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }
        School product = getItem(position);

        holder.productName.setText(product.school_name);

        return row;
    }

    class ViewHolder {
        public TextView productName;
    }
}
