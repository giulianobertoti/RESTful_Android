package com.example.giuliano.debugrest2;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultArea = (TextView) findViewById(R.id.result);



        //http://stackoverflow.com/questions/8654876/http-get-using-android-httpurlconnection
        //http://stackoverflow.com/questions/11810447/httpurlconnection-worked-fine-in-android-2-x-but-not-in-4-1-no-authentication-c

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final StringBuilder result = new StringBuilder();

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://api.myjson.com/bins/3gkfk");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);



            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                //System.out.print(current);
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }

        resultArea.setText(result);


    }



}
