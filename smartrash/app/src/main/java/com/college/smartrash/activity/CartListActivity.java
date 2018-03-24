package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.Model.NewOrderModel;
import com.college.smartrash.Model.SignModel;
import com.college.smartrash.adapter.CartListAdapter;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.databasehandler.Cart;
import com.college.smartrash.databasehandler.SignDetailsDb;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.college.smartrash.service.SignManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Faizal on 3/25/2017.
 */
public class    CartListActivity extends AppCompatActivity implements Appconst {


    private ListView cart_list;
    private CartListAdapter cartListAdapter;
    private TextView header_back_text;
    private ImageView header_back;
    private String activity_name;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);


        cart_list = (ListView) findViewById(R.id.cart_list);
        header_back_text = (TextView) findViewById(R.id.header_back_text);
        header_back = (ImageView) findViewById(R.id.header_back);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);


        Intent i = getIntent();
        activity_name = i.getStringExtra("ACTIVITY");

        List<CartModel> list = new ArrayList<CartModel>();
        list= new Cart(CartListActivity.this).getCartList();
        header_back_text.setText("My Cart");

        cartListAdapter = new CartListAdapter(CartListActivity.this, R.layout.view_model_cartlist, list);
        cart_list.setAdapter(cartListAdapter);


        if(list!=null){

            confirm.setVisibility(View.VISIBLE);

        }

        header_back.setOnClickListener(btnclick);
        confirm.setOnClickListener(btnclick);

    }

    public View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.confirm :{

                    SignModel signModel = new SignDetailsDb(CartListActivity.this).getSignDetails();
                    List<CartModel>  cartModelList  = new Cart(CartListActivity.this).getCartList();

                    Calendar c = Calendar.getInstance();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = df.format(c.getTime());


                    NewOrderModel sign=new NewOrderModel();
                    //sign.setUser_id(signModel.getUser_id());
                    sign.setCart_date(formattedDate);


                    CartModel order[]=cartModelList.toArray(new CartModel[cartModelList.size()]);

                    for (int i = 0; i < cartModelList.size(); i++) {

                        order[i] = cartModelList.get(i);

                    }

                    sign.setList(order);

                    new SignManageService(CartListActivity.this,REST_SERVICE_NEW_ORDER,sign,cartOrder);
                    new Cart(CartListActivity.this).delete();
                    Intent i = new Intent(CartListActivity.this, subscription.class);
                    startActivity(i);



                }
                break;


                case R.id.header_back:

                if (activity_name.equals(ACTIVITY_BOOK)) {

                    Intent i = new Intent(CartListActivity.this, BookActivity.class);
                    startActivity(i);

                } else if (activity_name.equals(ACTIVITY_HOME)) {

                    Intent j = new Intent(CartListActivity.this, HomeActivity.class);
                    startActivity(j);

                }
                    break;



            }

        }


    };



    private UiUpdateListener cartOrder = new UiUpdateListener() {

        @Override
        public void onSuccess(BasicResponse res) {

            try {

                if (res.errorCode == CODE_COMMUNICATION_SUCCESS) {


                    Toast.makeText(CartListActivity.this,res.getErrorMessage(),Toast.LENGTH_LONG).show();

                    Intent i=new Intent(CartListActivity.this,HomeActivity.class);
                    startActivity(i);

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





}