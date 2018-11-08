package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SuggestionListActivity extends AppCompatActivity implements HttpResponse {
    EditText restrauntNameText,addressText, cityText, cuisineText,distanceText, phoneNumberText;
    ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    TextView users;
    String selectedRestaurantId;

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
                "phone_number", "id"}, new int[]{R.id.name,
                R.id.address, R.id.city,R.id.distance,R.id.cuisine,R.id.phoneNumber, R.id.id});
        lv.setAdapter(adapter);
    }

    public void reviewClickHandler(View view) {
        LinearLayout parent = (LinearLayout) view.getParent();
        TextView restaurantId = (TextView) parent.getChildAt(7);
        selectedRestaurantId = restaurantId.getText().toString();

        // make request for reviews
        String request[] = new String[9];
        request[0] = "http://cuisine-point-mysql.herokuapp.com/reviews/id";
        request[1] = "POST";
        request[2] = "getReviewsById";
        request[3] = "" + selectedRestaurantId;

        HttpUrlRequest httpUrlRequest = new HttpUrlRequest();
        httpUrlRequest.delegate = SuggestionListActivity.this;
        httpUrlRequest.execute(request);
    }

    @Override
    public void getResponse(String response) throws JSONException {
        ArrayList<HashMap<String, String>> reviewList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray reviews = jsonObject.getJSONArray("result");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < reviews.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject e = reviews.getJSONObject(i);

            map.put("text", "Review: " + e.getString("text"));
            map.put("rating", "Rating: " + e.getString("rating"));

            try {
                String date = dateFormat.parse(e.getString("date")).toString();
                date = date.replace(" 00:00:00 PST", ",");
                //Toast.makeText(getApplicationContext(),"Date: " +date,Toast.LENGTH_LONG).show();
                map.put("date", "Date: " + date);
            } catch (Exception ex) {
                Log.e("SuggestionListActivity", "Exception:", ex);
            }

            reviewList.add(map);
        }

        Intent intent = new Intent(this, ReviewsListActivity.class);
        intent.putExtra("reviews", reviewList);
        intent.putExtra("restaurantId", selectedRestaurantId);
        startActivity(intent);
    }
}
