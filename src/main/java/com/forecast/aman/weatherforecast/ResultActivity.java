package com.forecast.aman.weatherforecast;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResultActivity extends AppCompatActivity {
    ImageView iv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;
    TextView tv12;

    String lat;
    String lng;
    JSONObject json;
    String city;
    String state;
    String degree;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FacebookSdk.sdkInitialize(getApplicationContext());



        String s=getIntent().getExtras().getString("Json");
        String street=getIntent().getExtras().getString("street");
        city=getIntent().getExtras().getString("city");
        state=getIntent().getExtras().getString("state");
        degree=getIntent().getExtras().getString("degree");

        tv1=(TextView)findViewById(R.id.tv_sum);
        tv2=(TextView)findViewById(R.id.tv_temp);
        tv3=(TextView)findViewById(R.id.tv_deg);
        tv4=(TextView)findViewById(R.id.tv_hl);
        tv5=(TextView)findViewById(R.id.tv_prec);
        tv6=(TextView)findViewById(R.id.tv_cor);
        tv7=(TextView)findViewById(R.id.tv_ws);
        tv8=(TextView)findViewById(R.id.tv_dp);
        tv9=(TextView)findViewById(R.id.tv_hum);
        tv10=(TextView)findViewById(R.id.tv_vis);
        tv11=(TextView)findViewById(R.id.tv_sur);
        tv12=(TextView)findViewById(R.id.tv_sus);
        iv=(ImageView)findViewById(R.id.icon_img);

        try {
            json=new JSONObject(s);
            lat=json.getString("latitude");
            lng=json.getString("longitude");
            String icon=json.getJSONObject("currently").getString("icon");
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

            tv1.setText(json.getJSONObject("currently").getString("summary")+" in "+city+", "+state);

            Double tem=Double.parseDouble(json.getJSONObject("currently").getString("temperature"));
            int i=tem.intValue();

            tv2.setText(i+"");
            String deg=null;
            if (degree.equals("Celsius")) {
                deg="\u00b0"+"C";
            }
            else
            {
                deg="\u00b0"+"F";
            }

            tv3.setText(deg);
            Double lt=Double.parseDouble(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(0)).getString("temperatureMin"));
            int ilt=lt.intValue();
            Double ht=Double.parseDouble(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(0)).getString("temperatureMax"));
            int iht=ht.intValue();
            tv4.setText("L:" + ilt + "°" + " | H:" + iht + "°");

            double precip=Double.parseDouble(json.getJSONObject("currently").getString("precipIntensity"));
            if (precip >= 0 && precip < 0.002) {
                tv5.setText("None");
            } else if (precip >= 0.002 && precip < 0.017) {
                tv5.setText("Very Light");
            } else if (precip >= 0.017 && precip < 0.1) {
                tv5.setText("Light");
            } else if (precip >= 0.1 && precip < 0.4) {
                tv5.setText("Moderate");
            } else if (precip >= 0.4) {
                tv5.setText("Heavy");
            }

            tv6.setText(Math.round(Double.parseDouble(json.getJSONObject("currently").getString("precipProbability"))*100)+"%");

            if (degree.equals("Celsius")) {
                tv7.setText(json.getJSONObject("currently").getString("windSpeed")+" m/s");
            }
            else
            {
                tv7.setText(json.getJSONObject("currently").getString("windSpeed")+" mph");
            }

            if (degree.equals("Celsius")) {
                tv8.setText(json.getJSONObject("currently").getString("dewPoint")+"\u00b0"+" C");
            }
            else
            {
                tv8.setText(json.getJSONObject("currently").getString("dewPoint")+"\u00b0"+" F");
            }

            String hum=json.getJSONObject("currently").getString("humidity");
            Double ihum=Double.parseDouble(hum);


            tv9.setText(Math.round(ihum * 100) + "%");

            double roundvis = Math.round((Double.parseDouble(json.getJSONObject("currently").getString("visibility"))) * 100d) / 100d;
            if (degree.equals("Celsius")) {
                tv10.setText(roundvis + " Km");
            }
            else
            {
                tv10.setText(roundvis + " mi");
            }


            Long timeStamp=Long.parseLong(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(0)).getString("sunriseTime"));
            String timezone=json.getString("timezone");
            java.util.Date t=new java.util.Date(timeStamp*1000);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // the format of your date
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            String formattedDate = sdf.format(t);


            tv11.setText(formattedDate);


            Long s_timeStamp=Long.parseLong(((JSONObject) json.getJSONObject("daily").getJSONArray("data").get(0)).getString("sunsetTime"));

            java.util.Date s_t=new java.util.Date(s_timeStamp*1000);
            SimpleDateFormat s_sdf = new SimpleDateFormat("hh:mm a"); // the format of your date

            s_sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            String s_formattedDate = s_sdf.format(s_t);


            tv12.setText(s_formattedDate);

         //   Toast.makeText(this,formattedDate, Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void show_detail(View v)
    {
        Intent in= new Intent(this,NewDetails.class);
        in.putExtra("json",json.toString());
        in.putExtra("city",city);
        in.putExtra("state",state);
        in.putExtra("degree",degree);
        startActivity(in);
    }
    public void view_map(View v)
    {
        Intent in=new Intent(this, Map_frame.class);
        in.putExtra("lat",lat);
        in.putExtra("lng",lng);
        startActivity(in);

    }
    public void share_fb(View v)
    {
        callbackManager = CallbackManager.Factory.create();
        ShareDialog shareDialog = new ShareDialog(this);
        // this part is optional
        String s=null;
        String image=null;

        try {
            Double tem=Double.parseDouble(json.getJSONObject("currently").getString("temperature"));
            int i=tem.intValue();
            String deg;
            if (degree.equals("Celsius")) {
                deg="\u00b0"+"C";
            }
            else
            {
                deg="\u00b0"+"F";
            }


            s=json.getJSONObject("currently").getString("summary")+", "+i+deg;

            String icon=json.getJSONObject("currently").getString("icon");
            if (icon.equals("partly-cloudy-day")) {
                image="cloud_day.png" ;
            } else if (icon.equals("partly-cloudy-night")) {
                image="cloud_night.png";
            } else if (icon.equals("clear-day")) {
                image="clear.png";
            } else if (icon.equals("clear-night")) {
                image="clear_night.png";
            } else if (icon.equals("rain")) {
                image="rain.png";
            } else if (icon.equals("snow")) {
                image="snow.png";
            } else if (icon.equals("sleet")) {
                image="sleet.png";
            } else if (icon.equals("wind")) {
                image="wind.png";
            } else if (icon.equals("fog")) {
                image="fog.png";
            } else if (icon.equals("cloudy")) {
                image="cloudy.png";
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ShareLinkContent linkContent;
        linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Current Weather in "+city+", "+state)
                    .setContentDescription(s)
                .setContentUrl(Uri.parse("http://forcast.io"))
                .setImageUrl(Uri.parse("http://cs-server.usc.edu:45678/hw/hw8/images/"+image))
                .build();

        shareDialog.show(linkContent);
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {

                @Override
                public void onSuccess(Sharer.Result result) {
                    Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_LONG).show();

                    Log.d("MyApp", "Share successful");
                    //Showed if I press the share or the cancel button

                }

                @Override
                public void onCancel() {
                    Toast.makeText(getApplicationContext(), "Posted Cancelled", Toast.LENGTH_LONG).show();
                    Log.d("MyApp", "Share canceled"); //Only showed when I press the close button
                }

                @Override
                public void onError(FacebookException e) {
                    Log.d("MyApp", "Share error: " + e.toString());
                }
            });


    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);



    }
    //        Intent in=new Intent(this, FB_activity.class);
//        startActivity(in);


}
