package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Faizal on 4/27/2017.
 */
public class HelpActivity extends AppCompatActivity {

    private ImageView header_back;
    private TextView header_back_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        header_back = (ImageView) findViewById(R.id.header_back);
        header_back_text = (TextView) findViewById(R.id.header_back_text);
        header_back_text.setText("Help");
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelpActivity.this, HomeActivity.class);
                startActivity(i);
            }

        });
    }
}

