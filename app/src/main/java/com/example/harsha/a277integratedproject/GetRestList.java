package com.example.harsha.a277integratedproject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class GetRestList extends AsyncTask<String, Void, String> {

    public JsonListResponse delegate =null;
    private String url,googleplaceData;

    @Override
    protected String doInBackground(String... strings) {
        url = (String) strings[0];
        Log.i("tag","URL for recommended restaraunts: "+url);

        DownloadUrl downloadUrl = new DownloadUrl();
        try
        {
            googleplaceData = downloadUrl.ReadTheURL(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e("Response", "response in onPostExecute " + s);
        delegate.processFinish(s);

    }
}
