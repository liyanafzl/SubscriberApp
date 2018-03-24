package com.college.smartrash.service;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.college.smartrash.Model.CartModel;
import com.college.smartrash.Model.NewOrderModel;
import com.college.smartrash.Model.SignModel;
import com.college.smartrash.activity.HomeActivity;
import com.college.smartrash.activity.LoginActivity;
import com.college.smartrash.assynchronoustask.JSONParser;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Android Developer on 6/3/2016.
 */
public class SignManageService implements Appconst {

    private Activity _activity;
    private int successCode;
    private JSONObject oneObject;

    private UiUpdateListener _uiupdateListner;
    private SignModel _model;
    private NewOrderModel cartModel;
    private String _service;

    private String data;

    public SignManageService(Activity activity, String service, SignModel model, UiUpdateListener uiupdateListner) {

        this._activity = activity;
        this._service = service;
        this._model = model;
        this._uiupdateListner = uiupdateListner;

        new JSONParse().execute();
    }

    public SignManageService(Activity activity, String service, NewOrderModel cartModel, UiUpdateListener uiupdateListner) {

        this._activity = activity;
        this._service = service;
        this.cartModel = cartModel;
        this._uiupdateListner = uiupdateListner;

        new JSONParse().execute();
    }


    private class JSONParse extends AsyncTask<String, String, JSONArray> {
        //private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }


        @Override
        protected JSONArray doInBackground(String... args) {

            JSONParser jParser = new JSONParser();
            Gson gson = new Gson();

            if(_service.equals(REST_SERVICE_NEW_ORDER))
            {

                data = gson.toJson(cartModel);
                Log.e(LOGTAG, "--------------order ----------->>>>   " + data);
                JSONArray json = jParser.postJSONFromUrl(_activity, data, _service);
                return json;

            }
            else
            {
                data = gson.toJson(_model);
                Log.e(LOGTAG, "------------------------->>>>   " + data);
                JSONArray json = jParser.postJSONFromUrl(_activity, data, _service);
                return json;

            }



        }


        @Override

        protected void onPostExecute(JSONArray jArray) {


            int length = jArray == null ? 0 : jArray.length();

            if (length > 0) {

                for (int i = 0; i < jArray.length(); i++) {

                    try {
                        oneObject = jArray.getJSONObject(i);
                        successCode = Integer.parseInt(oneObject.getString(MESSAGE_COMMUNICATION_SUCCESS));
                        if (successCode == CODE_COMMUNICATION_SUCCESS) {
                            if (_service.equals(REST_SERVICE_Login)) {

                                BasicResponse response = new BasicResponse();
                                Log.e(LOGTAG, "------------------------->>>>   " + oneObject.toString());
                                response.setErrorCode(CODE_COMMUNICATION_SUCCESS);
                                response.setErrorMessage(MESSAGE_COMMUNICATION_SUCCESS);
                                response.setObject(oneObject.getString("details"));
                                _uiupdateListner.onSuccess(response);



                            }
                            else if(_service.equals(REST_SERVICE_Signup)) {

                                BasicResponse response = new BasicResponse();
                                Log.e(LOGTAG, "------------------------->>>>   " + oneObject.toString());
                                response.setErrorCode(CODE_COMMUNICATION_SUCCESS);
                                response.setErrorMessage(MESSAGE_COMMUNICATION_SUCCESS);
                                response.setObject(oneObject);
                                _uiupdateListner.onSuccess(response);


                            }else if(_service.equals(REST_SERVICE_NEW_ORDER)) {

                                BasicResponse response = new BasicResponse();
                                response.setErrorCode(CODE_COMMUNICATION_SUCCESS);
                                response.setErrorMessage(MESSAGE_COMMUNICATION_ORDER_SUCCESS);
                                _uiupdateListner.onSuccess(response);


                            }else if(_service.equals(REST_SERVICE_NOTIFICATION)) {

                                BasicResponse response = new BasicResponse();
                                response.setErrorCode(CODE_COMMUNICATION_SUCCESS);
                                response.setErrorMessage(MESSAGE_COMMUNICATION_SUCCESS);
                                response.setObject(oneObject.getString("details"));
                                _uiupdateListner.onSuccess(response);


                            }
                        }else {

                                BasicResponse resp = new BasicResponse();
                                resp.setErrorCode(0);
                                resp.setErrorMessage(oneObject.getString(SUCCESS_MESSAGE));
                                _uiupdateListner.onError(resp);

                            }

                    }catch(JSONException e){
                        }
                    }
            }else
                {
                    BasicResponse response = new BasicResponse();
                    response.setErrorCode(CODE_SYSTEM_ERROR);
                    response.setErrorMessage("please wait....network busy");
                    response.setObject(null);
                    _uiupdateListner.onError(response);
                }
            }
        }
    }
