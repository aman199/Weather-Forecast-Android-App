package com.forecast.aman.weatherforecast;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    TextView tv1;
    Spinner sp;
    RadioButton rb1;
    RadioButton rb2;
    ImageView img ;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)(findViewById(R.id.text_error));
        et1 = (EditText) (findViewById(R.id.editText1));
        et2 = (EditText) (findViewById(R.id.editText2));
        sp=(Spinner)(findViewById(R.id.sp1));
        rb1=(RadioButton)(findViewById(R.id.radio1));
        rb2=(RadioButton)(findViewById(R.id.radio2));
        img = (ImageView)findViewById(R.id.imageView1);
        rg=(RadioGroup)findViewById(R.id.rg1);
        rb1.setChecked(true);


        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io/"));
                startActivity(intent);
            }
        });

    }

    public void go(View v) {
        String a, b,c,e;
        int d;
        int id=rg.getCheckedRadioButtonId();
        RadioButton rb=(RadioButton)findViewById(id);
        e=rb.getText().toString();

        a = et1.getText().toString();
        b = et2.getText().toString();
        c= sp.getSelectedItem().toString();
        d=sp.getSelectedItemPosition();



        tv1.setText("");
        if(a.isEmpty())
        {

           tv1.setText("Please enter a Street Address");
            return;
        }
        if(b.isEmpty())
        {
            tv1.setText("Please enter a City");
            return;
        }
        if(c.equals("Select"))
        {
            tv1.setText("Please select a State");
            return;
        }
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("amweb-env.elasticbeanstalk.com")

                    .appendQueryParameter("StreetAddress", a)
                    .appendQueryParameter("city", b)
                    .appendQueryParameter("state", new Statecodes().getscode(d))
                    .appendQueryParameter("degree", e);
            String myUrl = builder.build().toString();
            URL url=new URL(myUrl);
        //    URL url = new URL("http://amweb-env.elasticbeanstalk.com/?StreetAddress="+uri.e+"2825%2C+Ellendale+Pl&city=New+York&state=NY&degree=Fahrenheit");
            String json=new GetJson().execute(url).get();
            Intent in=new Intent(this, ResultActivity.class);
            in.putExtra("Json",json.toString());
            in.putExtra("street",a);
            in.putExtra("city", b);
            in.putExtra("state", new Statecodes().getscode(d));
            in.putExtra("degree",e);
            startActivity(in);

        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }



    }
    public void clc(View v) {
        et1.setText("");
        et2.setText("");
        sp.setSelection(0);
        rb1.setChecked(true);
        tv1.setText("");
    }

    public void abt(View v) {
        Intent in=new Intent(this, about.class);
        startActivity(in);

    }

    private class GetJson extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            HttpURLConnection urlconn=null;
            try {
                URL url=new URL(urls[0].toString());
                urlconn=(HttpURLConnection) url.openConnection();
                InputStream in=new BufferedInputStream(urlconn.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
                StringBuilder response=new StringBuilder();
                String input;
                while((input=br.readLine())!=null)
                {
                    response.append(input);
                }
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();

            }
            finally {
                urlconn.disconnect();
            }

            return null;

        }
    }

    private class Statecodes
    {
        private String[] states={"","AL","AK","AZ","AR","CA","CO","CT","DE",
                "DC","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR",
                "PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

        private String getscode(int i)
        {
            return states[i];
        }

    }
}

