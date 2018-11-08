package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelList extends AppCompatActivity {
    ListView lvh;
    ArrayList<HashMap<String, String>> restList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotellist);
        restList = new ArrayList<>();
        lvh = (ListView)findViewById(R.id.rlist);

        Intent intent = getIntent();
        String res = intent.getStringExtra("EXTRA_MESS");
        Log.i("res","Response in Hotellist intent "+ res);

        try {
            JSONObject obj = new JSONObject(res);
            JSONArray results = obj.getJSONArray("results");
            Log.i("results","Result array "+results);

            for(int i=0;i<results.length();i++)
            {
                JSONObject c = results.getJSONObject(i);
                String name =c.getString("name");
                String vicinity = c.getString("vicinity");
                HashMap<String,String> result = new HashMap<>();
                result.put("name",name);
                result.put("vicinity",vicinity);
                restList.add(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        ListAdapter adapter = new SimpleAdapter(
                HotelList.this, restList,
                R.layout.hotellistitem, new String[]{"name","vicinity"}, new int[]{R.id.name, R.id.vicinity});

        lvh.setAdapter(adapter);

    }
    public void onBack(View v){
        onBackPressed();
    }

    }


