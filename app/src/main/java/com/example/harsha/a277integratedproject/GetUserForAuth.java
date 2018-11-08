package com.example.harsha.a277integratedproject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserForAuth extends AsyncTask<String, Void, String> {
    String server_resp="test";
    private URL url;
    public JsonListResponse delegate =null;
    @Override
    protected String doInBackground(String... strings) {
        try{
            url = new URL(strings[0]);
            String reqType = strings[1];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            server_resp = readStream(urlConnection.getInputStream());
            Log.v("CatalogClient", server_resp);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return server_resp;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);

    }
}
