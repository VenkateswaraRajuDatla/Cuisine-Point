package com.example.harsha.a277integratedproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlRequest extends AsyncTask<String, Void, String> {

    String server_response="test";
    HttpResponse delegate = null;

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            String requestType = strings[1];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (requestType.equals("POST")) {
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));

                try {

                    String requestTable = strings[2];
                    JSONObject body = new JSONObject();

                    if (requestTable.equals("user")) {
                        body.put("firstname", strings[3]);
                        body.put("email", strings[4]);
                        body.put("username", strings[5]);
                        body.put("password", strings[6]);
                        body.put("phoneNumber", strings[7]);
                        body.put("isAdmin", strings[8]);
                    } else if (requestTable.equals("restaurant")){
                        body.put("name", strings[3]);
                        body.put("username", strings[4]);
                        body.put("address", strings[5]);
                        body.put("city", strings[6]);
                        body.put("cuisine", strings[7]);
                        body.put("phoneNumber", strings[8]);

                    } else if (requestTable.equals("review")) {
                        body.put("restaurantId", strings[3]);
                        body.put("reviewText", strings[4]);
                        body.put("reviewDate", strings[5]);
                    }
                    else if (requestTable.equals("getByCuisine")) {
                        body.put("cuisine", strings[3]);
                    }

                    writer.write(body.toString());
                    writer.flush();
                    writer.close();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

                urlConnection.connect();
                server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return server_response;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.getResponse(s);
        Log.e("Response", "" + s);

    }

    // Converting InputStream to String
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
}



