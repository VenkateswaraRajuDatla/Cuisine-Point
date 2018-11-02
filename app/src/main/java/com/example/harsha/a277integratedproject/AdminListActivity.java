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

public class AdminListActivity extends AppCompatActivity {
    EditText restrauntNameText,addressText, cityText, cuisineText, phoneNumberText;
    Button addRestarauntButton, editRestarauntButton, showRestaurantsButton;
    ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    TextView users;
    HttpUrlRequest httpUrlRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        users=findViewById(R.id.textView);
        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");

        JSONArray json =null;
        try {
            json = new JSONArray(message);
            for(int i=0;i<json.length();i++){
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject e = json.getJSONObject(i);
                map.put("name", "Restaraunt Name:" + e.getString("name"));
                map.put("address", "Address: " +  e.getString("address"));
                map.put("city", "City: " +  e.getString("city"));
                map.put("cuisine", "Cuisine: " +  e.getString("cuisine"));
                map.put("phone_number", "Phone Number: " +  e.getString("phone_number"));
                contactList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                AdminListActivity.this, contactList,
                R.layout.list_item, new String[]{"name", "address","city","cuisine",
                "phone_number"}, new int[]{R.id.name,
                R.id.address, R.id.city,R.id.cuisine,R.id.phoneNumber});

        lv.setAdapter(adapter);


    }
}
