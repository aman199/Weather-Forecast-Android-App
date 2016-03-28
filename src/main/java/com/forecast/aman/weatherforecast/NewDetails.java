package com.forecast.aman.weatherforecast;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class NewDetails extends AppCompatActivity {

    TextView tv_info;
    TextView tv_temp;
    JSONObject json;
    String city;
    String state;
    String degree;
    TableLayout tl1;
    TableLayout tl2;
    TableLayout tl3;
    TableLayout tl4;
    Button bt1;
    Button bt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
        tv_info=(TextView)findViewById(R.id.cityinfo);
        tv_temp=(TextView)findViewById(R.id.temp_id);
        tl1=(TableLayout)findViewById(R.id.hours_24);
        tl2=(TableLayout)findViewById(R.id.btplus);
        tl3=(TableLayout)findViewById(R.id.next_24);
        tl4=(TableLayout)findViewById(R.id.days_tab);
        bt1=(Button)findViewById(R.id.bt_hours);
        bt2=(Button)findViewById(R.id.bt_days);
        bt1.performClick();


        String s=getIntent().getExtras().getString("json");


        city=getIntent().getExtras().getString("city");
        state=getIntent().getExtras().getString("state");
        degree=getIntent().getExtras().getString("degree");

        tv_info.setText("More Details for "+city+", "+state);

        String deg=null;
        if (degree.equals("Celsius")) {
            deg="\u00b0"+"C";
        }
        else
        {
            deg="\u00b0"+"F";
        }

        tv_temp.setText("Temp("+deg+")");
        try {
            json=new JSONObject(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(int i=0;i<48;i++) {

            String hour = "tv" + (i+1) + "1";
            String image ="iv"+(i+1);
            String temp = "tv" + (i+1) + "2";

            try {
                int id1 = R.id.class.getDeclaredField(hour).getInt(null);
                int id2 = R.id.class.getDeclaredField(image).getInt(null);
                int id3 = R.id.class.getDeclaredField(temp).getInt(null);
                TextView tv = (TextView) findViewById(id1);
                ImageView iv =(ImageView)findViewById(id2);
                TextView tv2 = (TextView) findViewById(id3);

                Long timeStamp=Long.parseLong(((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i)).getString("time"));
                String timezone=json.getString("timezone");
                java.util.Date t=new java.util.Date(timeStamp*1000);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // the format of your date
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                String formattedDate = sdf.format(t);
                tv.setText(formattedDate);

                String icon=((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i)).getString("icon");
                if (icon.equals("partly-cloudy-day")) {
                    iv.setImageResource(R.drawable.cloud_day);
                } else if (icon.equals("partly-cloudy-night")) {
                    iv.setImageResource(R.drawable.cloud_night);
                } else if (icon.equals("clear-day")) {
                    iv.setImageResource(R.drawable.clear);
                } else if (icon.equals("clear-night")) {
                    iv.setImageResource(R.drawable.clear_night);
                } else if (icon.equals("rain")) {
                    iv.setImageResource(R.drawable.rain);
                } else if (icon.equals("snow")) {
                    iv.setImageResource(R.drawable.snow);
                } else if (icon.equals("sleet")) {
                    iv.setImageResource(R.drawable.sleet);
                } else if (icon.equals("wind")) {
                    iv.setImageResource(R.drawable.wind);
                } else if (icon.equals("fog")) {
                    iv.setImageResource(R.drawable.fog);
                } else if (icon.equals("cloudy")) {
                    iv.setImageResource(R.drawable.cloudy);
                }

                Double tem=Double.parseDouble(((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i)).getString("temperature"));
                int j=tem.intValue();

                tv2.setText(j+"");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i=0;i<24;i++) {

            String hour = "tv_2" + (i+1) + "1";
            String image ="iv_2"+(i+1);
            String temp = "tv_2" + (i+1) + "2";

            try {
                int id1 = R.id.class.getDeclaredField(hour).getInt(null);
                int id2 = R.id.class.getDeclaredField(image).getInt(null);
                int id3 = R.id.class.getDeclaredField(temp).getInt(null);
                TextView tv = (TextView) findViewById(id1);
                ImageView iv =(ImageView)findViewById(id2);
                TextView tv2 = (TextView) findViewById(id3);

                Long timeStamp=Long.parseLong(((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i+24)).getString("time"));
                String timezone=json.getString("timezone");
                java.util.Date t=new java.util.Date(timeStamp*1000);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // the format of your date
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                String formattedDate = sdf.format(t);
                tv.setText(formattedDate);

                String icon=((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i+24)).getString("icon");
                if (icon.equals("partly-cloudy-day")) {
                    iv.setImageResource(R.drawable.cloud_day);
                } else if (icon.equals("partly-cloudy-night")) {
                    iv.setImageResource(R.drawable.cloud_night);
                } else if (icon.equals("clear-day")) {
                    iv.setImageResource(R.drawable.clear);
                } else if (icon.equals("clear-night")) {
                    iv.setImageResource(R.drawable.clear_night);
                } else if (icon.equals("rain")) {
                    iv.setImageResource(R.drawable.rain);
                } else if (icon.equals("snow")) {
                    iv.setImageResource(R.drawable.snow);
                } else if (icon.equals("sleet")) {
                    iv.setImageResource(R.drawable.sleet);
                } else if (icon.equals("wind")) {
                    iv.setImageResource(R.drawable.wind);
                } else if (icon.equals("fog")) {
                    iv.setImageResource(R.drawable.fog);
                } else if (icon.equals("cloudy")) {
                    iv.setImageResource(R.drawable.cloudy);
                }

                Double tem=Double.parseDouble(((JSONObject) json.getJSONObject("hourly").getJSONArray("data").get(i+24)).getString("temperature"));
                int j=tem.intValue();

                tv2.setText(j+"");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(int i=1;i<=7;i++)
        {
            String date="tvd_"+i+"1";
            String image="ivd_"+i;
            String temp="tvd_"+i+"2";

            try {
                int id1 = R.id.class.getDeclaredField(date).getInt(null);
                int id2 = R.id.class.getDeclaredField(image).getInt(null);
                int id3 = R.id.class.getDeclaredField(temp).getInt(null);
                TextView tv = (TextView) findViewById(id1);
                ImageView iv = (ImageView) findViewById(id2);
                TextView tv2 = (TextView) findViewById(id3);

                String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
                String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                Long timeStamp=Long.parseLong(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(i)).getString("time"));
                String timezone=json.getString("timezone");
                java.util.Date t=new java.util.Date(timeStamp*1000);
                String day=days[t.getDay()];
                String month=months[t.getMonth()];

                tv.setText(day+", "+month+" "+t.getDate());

                String icon=((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(i)).getString("icon");
                if (icon.equals("partly-cloudy-day")) {
                    iv.setImageResource(R.drawable.cloud_day);
                } else if (icon.equals("partly-cloudy-night")) {
                    iv.setImageResource(R.drawable.cloud_night);
                } else if (icon.equals("clear-day")) {
                    iv.setImageResource(R.drawable.clear);
                } else if (icon.equals("clear-night")) {
                    iv.setImageResource(R.drawable.clear_night);
                } else if (icon.equals("rain")) {
                    iv.setImageResource(R.drawable.rain);
                } else if (icon.equals("snow")) {
                    iv.setImageResource(R.drawable.snow);
                } else if (icon.equals("sleet")) {
                    iv.setImageResource(R.drawable.sleet);
                } else if (icon.equals("wind")) {
                    iv.setImageResource(R.drawable.wind);
                } else if (icon.equals("fog")) {
                    iv.setImageResource(R.drawable.fog);
                } else if (icon.equals("cloudy")) {
                    iv.setImageResource(R.drawable.cloudy);
                }

                Double lt=Double.parseDouble(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(i)).getString("temperatureMin"));
                int ilt=lt.intValue();
                Double ht=Double.parseDouble(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(i)).getString("temperatureMax"));
                int iht=ht.intValue();
                if(degree.equals("Celsius")) {
                    tv2.setText("Min: " + ilt + "°C" + " | Max: " + iht + "°C");
                }
                else {
                    tv2.setText("Min: " + ilt + "°F" + " | Max: " + iht + "°F");
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


        }


    }
    public void show_next24(View v) {
        tl3.setVisibility(v.VISIBLE);
        tl2.setVisibility(v.GONE);


    }

    public void show_days(View v)
    {
        bt1.setBackgroundResource(R.drawable.green);
        bt2.setBackgroundResource(R.drawable.pink);
//        bt2.setBackgroundColor(Color.RED);
//        bt1.setBackgroundColor(Color.BLUE);
        tl1.setVisibility(v.GONE);
        tl2.setVisibility(v.GONE);
        tl3.setVisibility(v.GONE);
        tl4.setVisibility(v.VISIBLE);
    }
    public void show_hours(View v)
    {
        bt1.setBackgroundResource(R.drawable.pink);
        bt2.setBackgroundResource(R.drawable.green);
//        bt2.setBackgroundColor(Color.BLUE);
//        bt1.setBackgroundColor(Color.RED);
        tl1.setVisibility(v.VISIBLE);
        tl2.setVisibility(v.VISIBLE);
        tl3.setVisibility(v.GONE);
        tl4.setVisibility(v.GONE);
    }
}
