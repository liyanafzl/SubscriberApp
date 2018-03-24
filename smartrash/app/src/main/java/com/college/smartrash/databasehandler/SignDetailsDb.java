package com.college.smartrash.databasehandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.college.smartrash.Model.SignModel;
import com.college.smartrash.constant.Appconst;

public class SignDetailsDb implements Appconst {


    SQLiteDatabase mydb;
    private static String DBNAME = "SMARTRASH.db";

    private Context context;
    private SignModel model = null ;
    private long s=0;

    public SignDetailsDb(Context context)

    {
        this.context = (context);
    }


    public void insert(SignModel model) {
        try {
            
            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("DROP TABLE IF EXISTS signmanagersmartrash");
            mydb.execSQL("CREATE TABLE IF  NOT EXISTS signmanagersmartrash " +
                    "(SIGNID TEXT,SIGNUSER TEXT);");
                mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
               // mydb.execSQL("INSERT INTO signmanagersmartrash (SIGNID,SIGNUSER) " +
               //         "VALUES('" + model.getUser_id() + "','" + model.getUser_username() + "')");

                mydb.close();


        } catch (Exception e) {

            Log.e(LOGTAG, "Error in inserting into signmanagersmartrash table");

        }

    }

    /*public void update(SignModel model) {
        try {
            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("UPDATE signmanagersmartrash SET SIGNID='" + model.getUserId() + "',SIGNMOBILE='" + model.getUserMob() + "'," +
                    "SIGNPASSWORD='" + model.getUserPwd() + "',SIGNSTATUS='" + model.getUserStatus() +"' " +
                    "WHERE SIGNMOBILE='" + model.getUserMob() + "' ");

            mydb.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }*/


    public SignModel getSignDetails() {

            try{
                mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
                Cursor c  = mydb.rawQuery("SELECT * FROM signmanagersmartrash " , null);

            if (c.moveToFirst()) {

                do {

                    model = new SignModel();
                    model.setUser_id(c.getString(0));
                    model.setUser_username(c.getString(1));

                } while (c.moveToNext());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return model;

    }

    public void deleteSignDetails() {

        try {

            mydb = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("DROP TABLE IF EXISTS signmanagersmartrash");
            mydb.close();

        } catch (Exception e) {
            Log.e(LOGTAG, "Error in drop into Product table");

        }
    }

    }