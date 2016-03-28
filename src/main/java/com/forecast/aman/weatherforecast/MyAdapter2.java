package com.forecast.aman.weatherforecast;
import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.forecast.aman.weatherforecast.R.drawable.clear;


/**
 * Created by HarbirSingh on 11/21/2015.
 */
public class MyAdapter2 extends BaseAdapter {
    ArrayList<details2> al=new ArrayList<details2>();

    public MyAdapter2()
    {
        al.add(new details2("Sunday, Nov 08" ,"Min: 55F | Max: 78F"));
        al.add(new details2("Sunday, Nov 08", "Min: 55F | Max: 78F"));
        al.add(new details2("Sunday, Nov 08", "Min: 55F | Max: 78F"));
        al.add(new details2("Sunday, Nov 08", "Min: 55F | Max: 78F"));
        al.add(new details2("Sunday, Nov 08","Min: 55F | Max: 78F"));

    }

    @Override
    public int getCount()
    {
        return al.size();
    }

    @Override
    public Object getItem(int i)
    {
        return al.get(i);
    }

    @Override
    public long getItemId(int position)
    {
        return position+10;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {

        if(convertView==null)
        {
            LayoutInflater inflater= LayoutInflater.
                    from(parent.getContext());
            convertView=inflater.inflate(R.layout.singlerow2,
                    parent, false);
        }


        details2 dt2= al.get(i);

        TextView tv1,tv2;
        ImageView iv;

        tv1=(TextView)(convertView.findViewById(R.id.tv211));
        tv2=(TextView)(convertView.findViewById(R.id.tv222));
        iv=(ImageView)(convertView.findViewById(R.id.iv211));


        tv1.setText(dt2.day);
        //  Log.d("MY", "mi"+dt.temperature);
        tv2.setText(" "+dt2.hltemp);
        iv.setImageResource(R.drawable.clear);



        return convertView;
    }

}
