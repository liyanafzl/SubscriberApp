package com.college.smartrash.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.college.smartrash.Model.SignModel;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.college.smartrash.service.SignManageService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Faizal on 3/19/2017.
 */
public class SignUpActivity extends AppCompatActivity implements Appconst {

    private EditText signup_name;
    private EditText signup_mob;
    private EditText signup_loctype;
    private EditText signup_ad1;
    private EditText signup_ad2;
    private TextView signup_city;
    private TextView signup_lat;
    private TextView signup_long;

    private EditText signup_email;
    private EditText signup_pass;
    private EditText signup_confirm;
    private Boolean flag = false;

    private RadioGroup loc_radio_group;
    private RadioButton radio_button1;
    private RadioButton radio_button2;
    private RadioButton radio_button3;
    private RadioButton radio_button4;
    private RadioButton radio_button5;

    private Button signup_button;
    private Button signup_gps;
    private ImageView header_back;
    ImageView header_back_noti;

    public String li;
    public String lo;

    private LocationManager locationMangaer = null;
    private LocationListener locationListener = null;

    String str_name;
    String str_mob;
    String str_loctype;
    int loctype_id;
    String str_ad1;
    String str_ad2;

    String str_city;
    String str_pin;
    String str_email;
    String str_pass;
    String str_confirm;
    String str_addr;

    public SignModel sign;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_signup);

        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_mob = (EditText) findViewById(R.id.signup_mob);
        signup_ad1 = (EditText) findViewById(R.id.signup_ad1);
        signup_ad2 = (EditText) findViewById(R.id.signup_ad2);

        signup_city = (TextView) findViewById(R.id.signup_city);
        signup_lat = (TextView) findViewById(R.id.signup_lat);
        signup_long = (TextView) findViewById(R.id.signup_long);

        signup_email = (EditText) findViewById(R.id.signup_email);
        signup_pass = (EditText) findViewById(R.id.signup_pass);
        signup_confirm = (EditText) findViewById(R.id.signup_confirm);

        signup_button = (Button) findViewById(R.id.signup_button);
        signup_gps = (Button) findViewById(R.id.signup_gps);

        loc_radio_group = (RadioGroup) findViewById(R.id.loc_radio_group);


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name = signup_name.getText().toString();
                str_mob = signup_mob.getText().toString();
                str_ad1 = signup_ad1.getText().toString();
                str_ad2 = signup_ad2.getText().toString();
                str_city = signup_city.getText().toString();
                str_email = signup_email.getText().toString();
                str_pass = signup_pass.getText().toString();
                str_confirm = signup_confirm.getText().toString();
                str_addr = str_ad1 + str_ad2;
                if (!(str_pass.equals(str_confirm))) {
                    Toast.makeText(SignUpActivity.this, "Password doesn't match ", Toast.LENGTH_SHORT).show();
                }
                loctype_id = 1;

                radio_button1 = (RadioButton) findViewById(R.id.homeradio);
                radio_button2 = (RadioButton) findViewById(R.id.schoolradio);
                radio_button3 = (RadioButton) findViewById(R.id.hospitalradio);
                radio_button4 = (RadioButton) findViewById(R.id.industryradio);
                radio_button5 = (RadioButton) findViewById(R.id.othersradio);
                if(radio_button1.isChecked())
                    loctype_id =1;
                else if(radio_button2.isChecked())
                    loctype_id =3;
                else if(radio_button3.isChecked())
                    loctype_id =2;
                else if(radio_button4.isChecked())
                    loctype_id =4;
                else if(radio_button5.isChecked())
                    loctype_id =5;

                sign = new SignModel();
                sign.setUser_username(str_name);
                sign.setUser_password(str_pass);
                sign.setUser_address(str_ad1+str_ad2+str_city);
                sign.setUser_phone(str_mob);
                sign.setUser_longitude(lo);
                sign.setUser_latitude(li);
                sign.setUser_email(str_email);
                sign.setUser_loctype_id(loctype_id);

                new SignManageService(SignUpActivity.this, REST_SERVICE_Signup, sign, uiLogin);



            }
            public UiUpdateListener uiLogin = new UiUpdateListener()
            {
                @Override
                public void onSuccess(BasicResponse res) {

                    try {

                        if (res.errorCode == CODE_COMMUNICATION_SUCCESS) {


                            Toast.makeText(SignUpActivity.this, "You have successfully signed up", Toast.LENGTH_SHORT).show();
                            //Intent j = new Intent(SignUpActivity.this, HomeActivity.class);
                            //startActivity(j);

                        }

                    } catch (Exception e) {

                    }

                }


                @Override
                public void onError(BasicResponse res) {


                    Toast.makeText(getApplicationContext(), res.getErrorMessage(),
                            Toast.LENGTH_LONG).show();


                }
            };
        });
        locationMangaer = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        header_back = (ImageView) findViewById(R.id.header_back);

        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }

        });
        signup_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SignUpActivity.this, "Please wait while we collect your GPS location...", Toast.LENGTH_SHORT).show();
                flag = displayGpsStatus();
                if (flag) {

                    locationListener = new MyLocationListener();
                    try {
                        locationMangaer.requestLocationUpdates(LocationManager
                                .GPS_PROVIDER, 5000, 10, locationListener);
                    }catch (SecurityException e)
                    {}

                } else {
                    alertbox("Gps Status!!", "Your GPS is: OFF");
                }

            }

        });
    }

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disabled")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*----------Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {


            Toast.makeText(getBaseContext(), "Location changed : Lat: " +
                            loc.getLatitude() + " Lng: " + loc.getLongitude(),
                    Toast.LENGTH_SHORT).show();
            //double longitude =loc.getLongitude();

            //sign.setUser_latitude(longi);
            String latitude = "Latitude: " + loc.getLatitude();

            String longitude = "Longitude: " + loc.getLongitude();
             li = String.valueOf(loc.getLatitude());
            lo = String.valueOf(loc.getLongitude());
            //sign.setUser_latitude(li);
            //sign.setUser_longitude(lo);


    /*----------to get City-Name from coordinates ------------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(),
                    Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(), loc
                        .getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String s ="City: " + cityName;
            signup_city.setText(cityName);
            signup_lat.setText(latitude);
            signup_long.setText(longitude);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }


    }
}





