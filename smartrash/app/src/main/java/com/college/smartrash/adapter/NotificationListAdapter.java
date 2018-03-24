package com.college.smartrash.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.Model.NotificationModel;
import com.college.smartrash.Model.OrderstatusModel;
import com.college.smartrash.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizal on 4/24/2017.
 */
public class NotificationListAdapter  extends ArrayAdapter<OrderstatusModel> {


    private Context mContext;
    private int layoutResourceId;
    private List<OrderstatusModel> data = null;

    private TextView ordernumber ;
    private TextView orderdate ;
    private TextView orderstatus ;
    private TextView orderschedule;

    private String order_placed = "Your order has been placed successfully" ;


    public NotificationListAdapter(Context mContext, int layoutResourceId, List<OrderstatusModel> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;



        this.data = new ArrayList<OrderstatusModel>();
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        OrderstatusModel note = data.get(position);
        ordernumber = (TextView) convertView.findViewById(R.id.ordernumber);
        orderdate = (TextView) convertView.findViewById(R.id.orderdate);
        orderstatus = (TextView) convertView.findViewById(R.id.orderstatus);
        orderschedule = (TextView) convertView.findViewById(R.id.orderschedule);



        ordernumber.setText(note.getOrder_id());
        orderdate.setText(note.getO_date());
        orderstatus.setText(note.getStatus());
        orderschedule.setText(note.getO_schedule());



        return convertView;
    }
}
