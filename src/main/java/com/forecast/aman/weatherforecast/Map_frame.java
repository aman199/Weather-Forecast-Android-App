package com.forecast.aman.weatherforecast;


import android.app.Activity;




import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Map_frame extends Activity {

    FragmentManager frm;
    FragmentTransaction frt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_frame);

        String lat=getIntent().getExtras().getString("lat");
        String lng=getIntent().getExtras().getString("lng");

        frm= getFragmentManager();
        frt= frm.beginTransaction();

        map_fragment fragment = new map_fragment();
        Bundle bundle = new Bundle();
        bundle.putString("lat", lat);
        bundle.putString("lng", lng);

        fragment.setArguments(bundle);


        frt.add(R.id.fragment_container, fragment);

        frt.commit();



    }



}
