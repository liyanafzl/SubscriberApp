package com.college.smartrash.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.college.smartrash.adapter.PagerAdapter;

/**
 * Created by Faizal on 4/23/2017.
 */
public class ProcessActivity extends FragmentActivity {

    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        viewpager=(ViewPager)findViewById(R.id.pager);
        PagerAdapter padapter=new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

    }

}