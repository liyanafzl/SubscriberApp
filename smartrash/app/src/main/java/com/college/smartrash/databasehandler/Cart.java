package com.college.smartrash.databasehandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.constant.Appconst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizal on 4/23/2017.
 */
public class Cart implements Appconst {
    SQLiteDatabase mydb;
    private static String DBNAME = "SMARTRASH.db";

    private  List<CartModel>cartModelList ;
    private CartModel model = null ;
    private Context context;
    private long s=0;


    public Cart(Context context)

    {
        this.context = (context);
    }


    public void insertList(List<CartModel> modellist) {

       for(CartModel ca : modellist){

           Log.e(LOGTAG,"----name-----"+ca.getItem_name());

           CartModel cartModel = SerchCartDetailsByname(ca) ;

           if(cartModel!=null){

               update(ca,cartModel);

           }else{

               insert(ca);
           }

       }

 
    }

    public void insert(CartModel model) {
        try {

            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
          //  mydb.execSQL("DROP TABLE IF EXISTS cart");
            mydb.execSQL("CREATE TABLE IF  NOT EXISTS cart " +
                    "(NAME TEXT,PRICE TEXT,IMAGE TEXT);");
            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("INSERT INTO cart (NAME,PRICE,IMAGE) " +
                    "VALUES('" + model.getItem_name()+ "','" + model.getItem_cost()+ "','" + model.getItem_image() + "')");

            mydb.close();


        } catch (Exception e) {

            Log.e(LOGTAG, "Error in inserting into cart table");

        }

    }

    public void update(CartModel model,CartModel model2) {
        try {
            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("UPDATE cart SET NAME='" + model.getItem_name() + "'," +
                    "PRICE='" + model.getItem_cost() + "'," +
                    "IMAGE='" + model.getItem_image() +"' "
                    + "WHERE  NAME = '"+model2.getItem_name()+"' ");

            mydb.close();

        } catch (Exception e) {

            Log.e(LOGTAG, "Error in update into cart table");

        }
    }

    public CartModel SerchCartDetailsByname(CartModel cart) {

        try{
            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            Cursor c  = mydb.rawQuery("SELECT * FROM cart  WHERE  NAME = '"+cart.getItem_name()+"'" , null);

            if (c.moveToFirst()) {

                do {

                    model = new CartModel();
                    model.setItem_name(c.getString(0));
                    model.setItem_cost(c.getString(1));
                    model.setItem_image(c.getString(2));

                } while (c.moveToNext());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return model;

    }



    public List<CartModel> getCartList() {

        try{
            cartModelList = new ArrayList<CartModel>();

            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            Cursor c  = mydb.rawQuery("SELECT * FROM cart " , null);

            if (c.moveToFirst()) {

                do {

                    model = new CartModel();
                    model.setItem_name(c.getString(0));
                    model.setItem_cost(c.getString(1));
                    model.setItem_image(c.getString(2));
                    cartModelList.add(model);
                } while (c.moveToNext());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return cartModelList;

    }



    public void deleteProduct(CartModel cart) {

        try {

            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("DELETE FROM cart WHERE NAME = '"+cart.getItem_name()+"'");
            mydb.close();

        } catch (Exception e) {

            Log.e(LOGTAG, "Error in drop into cart table");

        }

    }


    public void delete() {

        try {

            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("DROP TABLE IF EXISTS cart");
            mydb.close();

        } catch (Exception e) {
            Log.e(LOGTAG, "Error in drop into Product table");

        }

    }

}



