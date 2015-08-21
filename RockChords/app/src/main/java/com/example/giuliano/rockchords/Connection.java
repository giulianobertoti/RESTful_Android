package com.example.giuliano.rockchords;

/**
 * Created by Giuliano on 02/06/2015.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Connection{

    private final String USER_AGENT = "Mozilla/5.0";


    // HTTP GET request
    public List<Music> sendGet() throws Exception {

        //https://api.myjson.com/bins/3kpyw
        //http://api.flickr.com/services/feeds/photos_public.gne?tags=beatles&format=json&jsoncallback=?
        String url = "https://api.myjson.com/bins/3gkfk";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //System.out.println(response.toString());

        List<Music> found = findAllItems(new JSONArray(response.toString()));

        return found;
    }

    public List<Music> findAllItems(JSONArray response) {

        List<Music> found = new LinkedList<Music>();

        try {


            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                found.add(new Music(obj.getString("chords"), obj.getString("band"), obj.getString("musicName")));
            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;
    }

}

