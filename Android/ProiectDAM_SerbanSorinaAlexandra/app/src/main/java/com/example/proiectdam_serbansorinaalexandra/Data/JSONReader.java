package com.example.proiectdam_serbansorinaalexandra.Data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONReader extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection)urls[0].openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();
            String line = "";
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            inputStreamReader.close();
            inputStream.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    protected List<Landmark> parceLandmarksJSON(String json) {
        List<Landmark> landmarks = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray landmarkObject = jsonObject.getJSONArray("landmarks");
            for (int i = 0; i < landmarkObject.length(); i++) {
                JSONObject currentObject = landmarkObject.getJSONObject(i);
                Landmark landmark = new Landmark();
                landmark.setName(currentObject.getString("name"));
                landmark.setLocation(currentObject.getString("location"));
                landmark.setType(currentObject.getString("type"));

                landmarks.add(landmark);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return landmarks;
    }
}
