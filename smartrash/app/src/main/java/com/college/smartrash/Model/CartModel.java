package com.college.smartrash.Model;

/**
 * Created by Faizal on 3/25/2017.
 */
public class CartModel {

    private String item_image ;
    private  String item_name ;
    private String item_cost ;


    public CartModel(){
    }



    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(String item_cost) {
        this.item_cost = item_cost;
    }
}
