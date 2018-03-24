package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.college.smartrash.Model.SignModel;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.constant.ServiceNamesConst;
import com.college.smartrash.databasehandler.Cart;
import com.college.smartrash.databasehandler.SignDetailsDb;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.college.smartrash.service.SignManageService;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Faizal on 3/10/2017.
 */
public class LoginActivity extends AppCompatActivity implements Appconst{

    private EditText login_username;
    private EditText login_password;
    private Button login_signin;
    private TextView login_signup;

    String str_username;
    String str_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login_signin = (Button) findViewById(R.id.login_signin);
        login_signup = (TextView) findViewById(R.id.login_signup);

        login_signin.setOnClickListener(btnclick);
        login_signup.setOnClickListener(btnclick);

    }


    public View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.login_signin: {

                    str_username = login_username.getText().toString();
                    str_password = login_password.getText().toString();

                    SignModel sign=new SignModel();
                    sign.setUser_username(str_username);
                    sign.setUser_password(str_password);

                    new SignManageService(LoginActivity.this,REST_SERVICE_Login,sign,uiLogin);
//                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(i);
                   // new Cart(LoginActivity.this).delete();





                }
                break;

                case R.id.login_signup: {

                    Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(i);

                }
                break;

            }
        }
    };



    private UiUpdateListener uiLogin = new UiUpdateListener() {

        @Override
        public void onSuccess(BasicResponse res) {

            try {

                if (res.errorCode == CODE_COMMUNICATION_SUCCESS) {

                    SignModel[] signdtails = getSigndetails(res.getObject().toString());

                   // new Cart(LoginActivity.this).delete();

                    SignModel model = new SignModel();
                    model.setUser_id(signdtails[0].getUser_id());
                    model.setUser_username(signdtails[0].getUser_username());

                    new SignDetailsDb(LoginActivity.this).insert(model);

                    Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                    startActivity(i);

                }

            } catch (Exception e) {

            }

        } ;

        @Override
        public void onError(BasicResponse res) {


            Toast.makeText(getApplicationContext(), res.getErrorMessage(),
                    Toast.LENGTH_LONG).show();


        }
    };


    private SignModel[] getSigndetails(String json) {

        SignModel[] pdtList=null;

        if (json != null && !"".equals(json.toString())) {

            Gson gson = new Gson();
            pdtList = gson.fromJson(json,  SignModel[].class);

        }

        return pdtList;

    }



}