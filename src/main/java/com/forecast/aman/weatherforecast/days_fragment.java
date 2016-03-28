package com.forecast.aman.weatherforecast;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class days_fragment extends Fragment {

    ListView lv2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_days_fragment, container,false);

    }
    public void onStart() {
        super.onStart();
        lv2=(ListView)(getActivity().findViewById(R.id.listView2));

        MyAdapter2 ad2=new MyAdapter2();

        lv2.setAdapter(ad2);
    }
}
