package com.example.david.quiz;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by david on 25.11.2017.
 */

class AsyncHttpHandler extends AsyncTask<Void, Void, String>
{
    @Override
    protected String doInBackground(Void... params)
    {

        String str="https://opentdb.com/api.php?amount=5&category=23&difficulty=easy&type=multiple";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String response = new String();
            for(String line; (line = bufferedReader.readLine()) != null; response += line);
            return response;
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            return "nope";
        }
    }
}
