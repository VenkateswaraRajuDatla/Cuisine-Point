package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SuggestionListActivity extends AppCompatActivity {
    EditText restrauntNameText,addressText, cityText, cuisineText,distanceText, phoneNumberText;
    ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    TextView users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        users=findViewById(R.id.textView);
        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();

        ArrayList<HashMap<String, String>> restarauntList =  (ArrayList<HashMap<String, String>>)getIntent().getSerializableExtra("restaraunts");

        ListAdapter adapter = new SimpleAdapter(
                SuggestionListActivity.this, restarauntList,
                R.layout.suggestion_list_item, new String[]{"name", "address","city","distance","cuisine",
                "phone_number"}, new int[]{R.id.name,
                R.id.address, R.id.city,R.id.distance,R.id.cuisine,R.id.phoneNumber});

        lv.setAdapter(adapter);


    }
}
