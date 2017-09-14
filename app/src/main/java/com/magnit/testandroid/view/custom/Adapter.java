package com.magnit.testandroid.view.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magnit.testandroid.R;
import com.magnit.testandroid.model.Values;

import java.util.ArrayList;


public class Adapter extends BaseAdapter {
    private ArrayList<Values> mValues;
    private LayoutInflater mLayoutInflater;

    public Adapter(Context context, ArrayList<Values> values) {
        this.mValues = values;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int i) {
        return mValues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.custom_item, viewGroup, false);
        }
        Values v = mValues.get(i);
        ((TextView) view.findViewById(R.id.textItem)).setText(String.valueOf(v.getValue()));
        ((Button) view.findViewById(R.id.buttonItem)).setRatio(v.getRatio());
        return view;
    }
}
