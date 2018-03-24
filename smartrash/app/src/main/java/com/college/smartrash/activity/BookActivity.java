package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.databasehandler.Cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Faizal on 3/24/2017.
 */
public class BookActivity extends AppCompatActivity implements Appconst {

    private TextView header_menu_text;
    private CheckBox checkplastic;
    private CheckBox checkpaper;
    private CheckBox checkmetaltin;
    private CheckBox checkglass;
    private CheckBox checkewaste;
    private CheckBox checkothers;
    private Button activity_book_addtocart;
    private ImageView header_menu;
    private ViewGroup home_leftnavigation;
    private ImageView headermenu_cart;
    private ImageView header_menu_noti;
    private DrawerLayout book_drawer_layout;
    private List<CartModel> string_array ;

    private TextView view_mode_home;
    private TextView view_mode_help;
    private TextView view_mode_orderstatus;

    private TextView view_mode_logout;

    private CartModel cartModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        book_drawer_layout=(DrawerLayout)findViewById(R.id.book_drawer_layout);
        checkplastic = (CheckBox) findViewById(R.id.checkplastic);
        checkpaper = (CheckBox) findViewById(R.id.checkpaper);
        checkmetaltin = (CheckBox) findViewById(R.id.checkmetaltin);
        checkglass = (CheckBox) findViewById(R.id.checkglass);
        checkewaste = (CheckBox) findViewById(R.id.checkewaste);
        checkothers = (CheckBox) findViewById(R.id.checkothers);
        activity_book_addtocart = (Button) findViewById(R.id.activity_book_addtocart);
        header_menu=(ImageView) findViewById(R.id.header_menu);
        home_leftnavigation=(ViewGroup) findViewById(R.id.home_leftnavigation);

        view_mode_home=(TextView)findViewById(R.id.view_mode_home);
        view_mode_orderstatus=(TextView)findViewById(R.id.view_mode_orderstatus);
        view_mode_logout=(TextView)findViewById(R.id.view_mode_logout);
        header_menu_text=(TextView)findViewById(R.id.header_menu_text);
        header_menu_noti=(ImageView)findViewById(R.id.header_menu_noti) ;

        headermenu_cart=(ImageView)findViewById(R.id.headermenu_cart);
        header_menu_noti=(ImageView)findViewById(R.id.header_menu_noti);

        activity_book_addtocart.setOnClickListener(btnClick);
        header_menu.setOnClickListener(btnClick);
        view_mode_help.setOnClickListener(btnClick);
        view_mode_home.setOnClickListener(btnClick);
        view_mode_orderstatus.setOnClickListener(btnClick);
        view_mode_logout.setOnClickListener(btnClick);
        headermenu_cart.setOnClickListener(btnClick);
       header_menu_noti.setOnClickListener(btnClick);
          header_menu_text.setText("Book A Pickup");
    }

    public View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()) {

                case R.id.activity_book_addtocart: {

                    string_array = new ArrayList<CartModel>();

                    if (checkplastic.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_plastic);
                        cartModel.setItem_cost("20");
                        cartModel.setItem_name(str_plastic);
                        string_array.add(cartModel);

                    }

                    if (checkpaper.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_paper);
                        cartModel.setItem_cost("60");
                        cartModel.setItem_name(str_paper);
                        string_array.add(cartModel);

                    }

                    if (checkmetaltin.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_metaltin);
                        cartModel.setItem_cost("70");
                        cartModel.setItem_name(str_metaltin);
                        string_array.add(cartModel);

                    }

                    if (checkglass.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_glass);
                        cartModel.setItem_cost("30");
                        cartModel.setItem_name(str_glass);
                        string_array.add(cartModel);

                    }

                    if (checkewaste.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_ewaste);
                        cartModel.setItem_cost("--");
                        cartModel.setItem_name(str_ewaste);
                        string_array.add(cartModel);

                    }

                    if (checkothers.isChecked()) {

                        cartModel = new CartModel() ;
                        cartModel.setItem_image(str_others);
                        cartModel.setItem_cost("20");
                        cartModel.setItem_name(str_others);
                        string_array.add(cartModel);

                    }

                    if (string_array.size() > 0) {

                        new Cart(BookActivity.this).insertList(string_array);

                    }
                }
                Toast.makeText(BookActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                break;


                case R.id.view_mode_home:

                    Intent i = new Intent(BookActivity.this, HomeActivity.class);
                    startActivity(i);


                break;

                case R.id.view_mode_orderstatus:
                    Intent j = new Intent(BookActivity.this, OrderListActivity.class);
                    startActivity(j);
                    break;

                case R.id.view_mode_logout:
                    Toast.makeText(BookActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                    Intent k=new Intent(BookActivity.this,LoginActivity.class);
                    startActivity(k);
                    break;

                case R.id.header_menu:
                    book_drawer_layout.openDrawer(home_leftnavigation);
                break;

                case R.id.headermenu_cart:

                    Intent l=new Intent(BookActivity.this,CartListActivity.class);
                    l.putExtra("ACTIVITY",ACTIVITY_BOOK);
                    startActivity(l);
                    break;


               case R.id.header_menu_noti:
                   Intent p=new Intent(BookActivity.this,NotificationActivity.class);
                        startActivity(p);
                   break;




            }


        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent i=new Intent(BookActivity.this,HomeActivity.class);
            startActivity(i);
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onBackPressed() {

        Intent i=new Intent(BookActivity.this,HomeActivity.class);
        startActivity(i);


    }

}