    package com.college.smartrash.activity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;

    /**
     * Created by Faizal on 3/24/2017.
     */


    public class GetRateActivity extends AppCompatActivity {

        private ImageView header_back;
        private TextView header_back_text;
        private ImageView header_menu_noti;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            header_back = (ImageView) findViewById(R.id.header_back);
            header_back_text = (TextView) findViewById(R.id.header_back_text);
            header_menu_noti=(ImageView)findViewById(R.id.header_menu_noti);

            header_back_text.setText("Get Rates");
//            header_menu_noti.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent a= new Intent(GetRateActivity.this, NotificationActivity.class);
//                    startActivity(a);
//
//                }
//
//            });
            header_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent z = new Intent(GetRateActivity.this, HomeActivity.class);
                    startActivity(z);

                }

            });

        }
    }