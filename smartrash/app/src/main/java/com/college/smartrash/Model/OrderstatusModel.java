package com.college.smartrash.Model;

/**
 * Created by Faizal on 3/25/2017.
 */
public class OrderstatusModel {


    private String order_id;
    private String o_date;
    private String status;
    private String o_schedule;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getO_schedule() {
        return o_schedule;
    }

    public void setO_schedule(String o_schedule) {
        this.o_schedule = o_schedule;
    }
}
