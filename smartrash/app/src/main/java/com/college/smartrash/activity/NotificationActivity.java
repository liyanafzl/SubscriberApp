package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.college.smartrash.Model.OrderstatusModel;
import com.college.smartrash.Model.SignModel;
import com.college.smartrash.adapter.NotificationListAdapter;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.constant.ServiceNamesConst;
import com.college.smartrash.databasehandler.Cart;
import com.college.smartrash.databasehandler.SignDetailsDb;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.college.smartrash.service.SignManageService;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Faizal on 4/24/2017.
 */
public class NotificationActivity extends AppCompatActivity implements Appconst{

    private TextView header_back_text;
    private NotificationListAdapter notificationListAdapter;
    private ImageView header_back_noti;
    private ImageView header_back;

    private ListView notification_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification_list = (ListView) findViewById(R.id.notification_list);
        header_back=(ImageView) findViewById(R.id.header_back);
        header_back_text=(TextView)findViewById(R.id.header_back_text);
        header_back_noti=(ImageView) findViewById(R.id.header_back_noti);

        header_back_text.setText("Notification");
        header_back_noti.setVisibility(View.GONE);
        header_back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(NotificationActivity.this, HomeActivity.class);
                                startActivity(i);
                                           }
                                       });

                SignModel signModel  = new SignDetailsDb(NotificationActivity.this).getSignDetails();

        SignModel signModel1  = new SignModel();
        //signModel1.setUser_id(signModel .getUser_id());

        new SignManageService(NotificationActivity.this, ServiceNamesConst.REST_SERVICE_NOTIFICATION,signModel1,uiupdaater);


    }

    private UiUpdateListener uiupdaater = new UiUpdateListener() {

        @Override
        public void onSuccess(BasicResponse res) {

            try {

                if (res.errorCode == CODE_COMMUNICATION_SUCCESS) {

                    OrderstatusModel[] list = getAllOrder(res.getObject().toString());

                    List<OrderstatusModel> orderstatusModels = Arrays.asList(list);

                    notificationListAdapter = new NotificationListAdapter(NotificationActivity.this,
                            R.layout.view_model_orderstatus, orderstatusModels);
                    notification_list.setAdapter(notificationListAdapter);


                }

            } catch (Exception e) {

            }

        } ;

        @Override
        public void onError(BasicResponse res) {


            Toast.makeText(getApplicationContext(), res.getErrorMessage(),
                    Toast.LENGTH_LONG).show();


        }
    };



    private OrderstatusModel[] getAllOrder(String json) {

        OrderstatusModel[] pdtList=null;

        if (json != null && !"".equals(json.toString())) {

            Gson gson = new Gson();
            pdtList = gson.fromJson(json,  OrderstatusModel[].class);

        }

        return pdtList;

    }

}
