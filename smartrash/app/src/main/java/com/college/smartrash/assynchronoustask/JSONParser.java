
package com.college.smartrash.assynchronoustask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.college.smartrash.constant.Appconst;



public class JSONParser implements Appconst {

	Activity _activity;
   static InputStream is = null;
   static JSONArray jObjArray = null;
   static String jsonString = "";
	String _service;

   // constructor
   public JSONParser() {

   }

   public JSONArray postJSONFromUrl(Activity activity,String data,	String service) {

       // Making HTTP request
       try {
    	   
    	   
    	   _activity = activity;
    	   
           // defaultHttpClient
           DefaultHttpClient httpClient = new DefaultHttpClient();
          HttpPost httpPost = new HttpPost(service);
           
     		httpPost.setHeader("Content-Type", "application/json");
     		httpPost.setHeader("Accept", "application/json");

           StringEntity se = new StringEntity(data);
              se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
              httpPost.setEntity(se);


           HttpResponse httpResponse = httpClient.execute(httpPost);

           HttpEntity httpEntity = httpResponse.getEntity();
           is = httpEntity.getContent();

       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

       try {
           BufferedReader reader = new BufferedReader(new InputStreamReader(
                   is, "iso-8859-1"), 8);
           StringBuilder sb = new StringBuilder();
           String line = null;
           while ((line = reader.readLine()) != null) {
               sb.append(line );
           }


           is.close();
           jsonString = sb.toString();

       } catch (Exception e) {
           Log.e("Buffer Error", "Error converting result " + e.toString());
       }

       // try parse the string to a JSON object
       try {


           Log.e(LOGTAG,"-----data-----"+data +"-----json parser----"+jsonString);

    	   jObjArray = new JSONArray(jsonString);



       } catch (Exception e) {
           Log.e("JSON Parser", "Error parsing data " + e.toString());
       }

       // return JSON String
       return jObjArray;

   }


   
}