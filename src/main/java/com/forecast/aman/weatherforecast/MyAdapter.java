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
public class MyAdapter extends BaseAdapter {
    ArrayList<detail> al=new ArrayList<detail>();

    public MyAdapter()
    {
        al.add(new detail("abc" ,90));
        al.add(new detail("xyz", 92));
        al.add(new detail("pqr", 98));
        al.add(new detail("mno", 85));
        al.add(new detail("def", 89));
        al.add(new detail("abc" ,90));
        al.add(new detail("xyz", 92));
        al.add(new detail("pqr", 98));
        al.add(new detail("mno", 85));
        al.add(new detail("def", 89));

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
            convertView=inflater.inflate(R.layout.singlerow,
                    parent, false);
        }


        detail dt= al.get(i);

        TextView tv1,tv2;
        ImageView iv;

        tv1=(TextView)(convertView.findViewById(R.id.tv11));
        tv2=(TextView)(convertView.findViewById(R.id.tv22));
        iv=(ImageView)(convertView.findViewById(R.id.iv11));


        tv1.setText(dt.time);
      //  Log.d("MY", "mi"+dt.temperature);
        tv2.setText(" " + dt.temperature);
        iv.setImageResource(R.drawable.clear);



        return convertView;
    }

}
