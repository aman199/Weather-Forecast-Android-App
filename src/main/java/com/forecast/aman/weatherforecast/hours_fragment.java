package com.forecast.aman.weatherforecast;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class hours_fragment extends Fragment {
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_hours_fragment, container,false);

    }
    public void onStart() {
        super.onStart();
        lv=(ListView)(getActivity().findViewById(R.id.listView1));

        MyAdapter ad=new MyAdapter();

        lv.setAdapter(ad);
    }

}
