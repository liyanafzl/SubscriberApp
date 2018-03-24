package com.college.smartrash.listener.listener;


import com.college.smartrash.response.BasicResponse;

/**
 * Created by Android Developer on 6/6/2016.
 */
public interface OnRestActionListener {

    public void onSuccess(BasicResponse res, int requestCode);
    public void onError(BasicResponse res, int requestCode);


}
