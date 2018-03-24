package com.college.smartrash.response;

import java.io.Serializable;

/**
 * Created by Android Developer on 6/6/2016.
 */



public class BasicResponse implements Serializable{

    public int errorCode;
    public String ErrorMessage;
    public Object object;


    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode)   {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return ErrorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }




    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("BasicResponse [errorCode = ");
        sb.append(errorCode);
        sb.append(",ErrorMessage = ");
        sb.append(ErrorMessage);
        sb.append(",Object = ");
        sb.append(object == null ? "NULL" : object.toString());
        sb.append("]");
        return sb.toString();
    }



}
