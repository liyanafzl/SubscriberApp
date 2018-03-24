package com.college.smartrash.Model;

import com.college.smartrash.databasehandler.Cart;

/**
 * Created by Faizal on 4/24/2017.
 */
public class NewOrderModel {


    private String user_id ;
    private String cart_date ;
    private CartModel[] list ;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCart_date() {
        return cart_date;
    }

    public void setCart_date(String cart_date) {
        this.cart_date = cart_date;
    }

    public CartModel[] getList() {
        return list;
    }

    public void setList(CartModel[] list) {
        this.list = list;
    }
public void setList(){
    this.list=null;
}

}
