package com.college.smartrash.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizal on 3/25/2017.
 */
public class CartListAdapter extends ArrayAdapter<CartModel> {


    Context mContext;
    int layoutResourceId;
    List<CartModel> data = null;

    private String str_plastic = "Plastic" ;
    private String str_paper= "Paper" ;
    private String str_metaltin= "Metal Tin" ;
    private String str_glass= "Glass" ;
    private String str_ewaste= "E-waste" ;
    private String str_others= "Others" ;
    private String str_bacteria = "Bacteria";
    private String str_compost = "Compost";

    public CartListAdapter(Context mContext, int layoutResourceId, List<CartModel> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;



        this.data = new ArrayList<CartModel>();
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        CartModel cart = data.get(position);

        TextView cart_name = (TextView) convertView.findViewById(R.id.cart_name);
        TextView cart_price = (TextView) convertView.findViewById(R.id.cart_price);
        ImageView cart_image = (ImageView) convertView.findViewById(R.id.cart_image);

        cart_name.setText(cart.getItem_name());
        cart_price.setText(cart.getItem_cost());


        if(cart.getItem_image().equals(str_plastic)){

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.beverage_bottles);
            cart_image.setImageDrawable(myDrawable);

        }else if(cart.getItem_image().equals(str_ewaste)) {

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.ewaste);
            cart_image.setImageDrawable(myDrawable);

        }else if(cart.getItem_image().equals(str_glass)) {

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.glass);
            cart_image.setImageDrawable(myDrawable);

        }else if(cart.getItem_image().equals(str_metaltin)) {

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.metal_cans);
            cart_image.setImageDrawable(myDrawable);

        }else if(cart.getItem_image().equals(str_paper)){

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.newspapers);
            cart_image.setImageDrawable(myDrawable);


        }else if(cart.getItem_image().equals(str_others)){

            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.products);
            cart_image.setImageDrawable(myDrawable);

        }
//        else if(cart.getItem_image().equals(str_bacteria)){
//
//            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.icon);
//            cart_image.setImageDrawable(myDrawable);
//
//        }
//        else if(cart.getItem_image().equals(str_compost)){
//
//            Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.icon);
//            cart_image.setImageDrawable(myDrawable);
//
//        }

        return convertView;
    }
}