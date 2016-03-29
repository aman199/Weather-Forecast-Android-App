package com.forecast.aman.weatherforecast;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class DetailsActivity extends FragmentActivity {
    FragmentManager frm;
    FragmentTransaction frt;
    Button bt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bt=(Button)(findViewById(R.id.bt_hours));
        frm= getSupportFragmentManager();
        bt.performClick();
    }
    
    public void show_hours(View v)
    {
        frt= frm.beginTransaction();

        frt.replace(R.id.ll1, new hours_fragment());

        frt.commit();
    }
    
    public void show_days(View v)
    {
        frt= frm.beginTransaction();

        frt.replace(R.id.ll1, new days_fragment());

        frt.commit();
    }
    
}
