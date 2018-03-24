package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.Model.OrderstatusModel;
import com.college.smartrash.adapter.CartListAdapter;
import com.college.smartrash.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizal on 3/25/2017.
 */
public class OrderListActivity extends AppCompatActivity {

    private ListView order_list;
    private OrderListAdapter orderListAdapter ;
    private ImageView header_back;
    private TextView header_back_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderstatus);
/*
        order_list = (ListView)findViewById(R.id.order_list);
        header_back=(ImageView)findViewById(R.id.header_back);
        header_back_text=(TextView)findViewById(R.id.header_back_text);

        List<OrderstatusModel> list = new ArrayList<OrderstatusModel>();
den
        OrderstatusModel c1= new OrderstatusModel("1","jhgf","dg");
        list.add(c1);

        OrderstatusModel c2= new OrderstatusModel("2","sjdgh","sdg");
        list.add(c2);

        OrderstatusModel c3 = new OrderstatusModel("3","sjdf","sdgsg");
        list.add(c3);

        OrderstatusModel c4= new OrderstatusModel("4","hjxv","sdgxb");
        list.add(c4);


        orderListAdapter = new OrderListAdapter(OrderListActivity.this, R.layout.view_model_orderstatus, list);
        order_list.setAdapter(orderListAdapter);
        header_back_text.setText("Order Status");
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderListActivity.this, HomeActivity.class);
                startActivity(i);
            }

        });*/

    }
}



