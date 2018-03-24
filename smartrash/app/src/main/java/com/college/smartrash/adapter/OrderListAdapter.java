package com.college.smartrash.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.college.smartrash.Model.OrderstatusModel;
import com.college.smartrash.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizal on 3/25/2017.
 */
public class OrderListAdapter extends ArrayAdapter<OrderstatusModel> {

    Context mContext;
    int layoutResourceId;
    List<OrderstatusModel> data = null;

    public OrderListAdapter(Context mContext, int layoutResourceId, List<OrderstatusModel> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = new ArrayList<OrderstatusModel>();
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        OrderstatusModel order = data.get(position);

        TextView ordernumber = (TextView) convertView.findViewById(R.id.ordernumber);
        TextView orderdate = (TextView) convertView.findViewById(R.id.orderdate);
        TextView orderstatus=(TextView) convertView.findViewById(R.id.orderstatus);

//        ordernumber.setText(order.getNumber());
//        orderdate.setText(order.getDate());
//        orderstatus.setText(order.getStatus());

        return convertView;
    }
}
